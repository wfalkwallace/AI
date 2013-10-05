$(function () {

	// Datepicker
	$('.input-daterange').datepicker({
		format: "yyyy-mm-dd",
		startDate: "-14d",
		todayBtn: "linked",
		autoclose: true
	});

	// Credentials
	var credentials = 
	{
		consumer_key: 'sZU3etBMqj7BeHEetZO6w',
		consumer_secret: 'rWxPl3oWesD31Gg56SLsXGAcGkyL9ux0LhlAncGK8A',
		access_token: '1932368732-nOVLlgcqp5ATBG3VAZrwLopVsuEj3BFysho4cqR',
		access_token_secret: 'qThVWaxB7llq1LrS4Ee3mM5iK18n410Jxbzoc4Q2aUo'
	};
	
	writeCredInfo();
	// show on the UI:
	// would be a username or something with 
	// sign in implemented
	function writeCredInfo() {
		$('#infowell').append('<h3>Using Credentials:</h3><ul><li id="consumer_key"><strong>Consumer Key: </strong></li><li id="consumer_secret"><strong>Consumer Secret: </strong></li><li id="access_token"><strong>Access Token: </strong></li><li id="access_token_secret"><strong>Access Token Secret: </strong></li></ul>');
		$('#consumer_key').append(credentials.consumer_key);
		$('#consumer_secret').append(credentials.consumer_secret);
		$('#access_token').append(credentials.access_token);
		$('#access_token_secret').append(credentials.access_token_secret);
	};
	
	function writeSearchInfo() {
		$('#infowell').append('<h3>Showing tweets matching: </h3>' +
		                      '<ul><li id="keytab"><strong>Keywords: </strong></li>' + 
		                      '<li id="loctab"><strong>Place: </strong></li>' + 
		                      '<li id="datetab"><strong>Dates: </strong></li></ul>');
	};
	
	// Codebird Initialization
	var bird = new Codebird;
	bird.setConsumerKey(credentials.consumer_key, credentials.consumer_secret);
	bird.setToken(credentials.access_token, credentials.access_token_secret);

	$('#infotab').click(function infotabclick() {
		$('#credentialtab').removeClass('active');
		$('#infotab').addClass('active');
		$('#infowell').empty();
		writeSearchInfo();
	});
	
	$('#credentialtab').click(function credtabclick() {
		$('#infotab').removeClass('active');
		$('#credentialtab').addClass('active');
		$('#infowell').empty();
		writeCredInfo();
	});

	$('#search-form').submit(function search_submit() {
		var keywords = $('#searchbar').val();
		var location = $('#locationbar').val();
		var since = $('#sincebar').val();
		var until = $('#untilbar').val();
		
		//VALIDATION
		// keywords && location && since && until;

		//update the info well display to search info
		$('#credentialtab').removeClass('active');
		$('#infotab').addClass('active');
		$('#infowell').empty();
		writeSearchInfo();
		$('#keytab').append(keywords);
		$('#loctab').append(location);
		$('#datetab').append(since + ' to ' + until);

		
		// prepare the geosearch parameters
		if(location){
			
			var geoargs = {
				query: location,
			};
			
			bird.__call(
			            "geo_search",
			            geoargs,
			            function geolookup(reply) {
			            	console.dir(reply);
			            	var location_id = reply.result.places && reply.result.places[0].id;

			            	if(location_id) {
			            		var params = {
			            			since: since,
			            			until: until, 
			            			q: keywords + " place:" + location_id,
			            			count: "50"
			            		};	
			            	}
			            	else {
			            		var params = { 
			            			since: since,
			            			until: until, 
			            			q: keywords,
			            			count: "50"
			            		}
			            	};	
			            	bird.__call(
			            	            "search_tweets",
			            	            params,
			            	            tweetparse
			            	            );
			            });
			
		}
		else {
			var params = { 
				since: since,
				until: until, 
				q: keywords,
				count: "50"
			};	
			bird.__call(
			            "search_tweets",
			            params,
			            tweetparse
			            );
		}
		return false;
	});


var templates={ 
	tweet: 
	'<div class="well"><div class="container"><div class="pull-left"><a href="https://www.twitter.com/<%=user.screen_name %>" target="_blank"><img src="<%=user.profile_image_url %>"></a></div><div class="pull-right"><h2><a href="https://www.twitter.com/<%=user.screen_name %>" target="_blank" style="text-decoration:none;color:#<%=user.profile_link_color %>"><%=user.screen_name %></a></h2></div></div><div class="panel panel-default"><div class="panel-body"><h4><%=text %></h4></div></div><div class="container"><div class="row-fluid"><div class="col-md-6"><h4><strong>Tweeted On: </strong><%=created_at %></h4></div><div class="col-md-6"><h4><a href="https://www.twitter.com/<%=user.screen_name %>/status/<%=id_str %>" target="_blank" style="text-decoration:none;color:#<%=user.profile_link_color %>">Original Tweet</a></h4></div></div></div></div>',
	tweet_loc: 
	'<div class="well"><div class="container"><div class="pull-left"><a href="https://www.twitter.com/<%=user.screen_name %>" target="_blank"><img src="<%=user.profile_image_url %>"></a></div><div class="pull-right"><h2><a href="https://www.twitter.com/<%=user.screen_name %>" target="_blank" style="text-decoration:none;color:#<%=user.profile_link_color %>"><%=user.screen_name %></a></h2></div></div><div class="panel panel-default"><div class="panel-body"><h4><%=text %></h4></div></div><div class="container"><div class="row-fluid"><div class="col-md-4"><h4><strong>Tweeted From: </strong><a href="https://twitter.com/search?q=place%3A<%=place.id_str %>" target="_blank" style="text-decoration:none;color:#<%=user.profile_link_color %>"><%=place.full_name %></a></h4></div><div class="col-md-4"><h4><strong>On: </strong><%=created_at %></h4></div><div class="col-md-4"><h4><a href="https://www.twitter.com/<%=user.screen_name %>/status/<%=id_str %>" target="_blank" style="text-decoration:none;color:#<%=user.profile_link_color %>">Original Tweet</a></h4></div></div></div></div>'
};


	//sep
	function tweetparse(reply) {
		console.dir(reply);
		
		$('#content').empty();
		
		var tweets = reply && reply.statuses;
		if(!reply) {
			$('#content').text('<div class="well danger"><h3>No search results were found for your search:</h3><br/><h3>try expanding your dates, searching a broader geographic region, or less specific keywords.</h3></div>');
		}
		tweets.forEach(function(response){
			response.created_at = response.created_at.replace(' +0000 2013', '');
			if(response.place){
				$('#content').append(_.template(templates.tweet_loc, response));
			}
			else {
				$('#content').append(_.template(templates.tweet, response));
			}
			
		});
		




	};


});






// _.each(reply.statuses, function printstatus(element, index, list) { 
// 	element.place && $('#content').append(
// 	                     '<div class="well"><div class="container"><h4 class="pull-left">' + element.user.screen_name + ':</h4>' +
// 	                     '<img src="' + element.user.profile_image_url + '" class="pull-right"></div>' +
// 	                     '<pre><code>' + index + ': ' + element.text + '<br/>' + 
// 	                     'place: ' + element.place.full_name + '</code></pre></div>'
// 	                     );
// });