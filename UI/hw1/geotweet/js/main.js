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
	
	// Codebird Initialization
	var bird = new Codebird;
	bird.setConsumerKey(credentials.consumer_key, credentials.consumer_secret);
	bird.setToken(credentials.access_token, credentials.access_token_secret);


	// bird.__call(
	//             "oauth_requestToken",
	//             {oauth_callback: "oob"},
	//             function (reply) {
	//             console.dir(reply);
	//         	bird.setToken(reply.oauth_token, reply.oauth_token_secret);
	//         });



$('#search-form').submit(function search_submit() {
	var keywords = $('#searchbar').val();
	var location = $('#locationbar').val();
	var since = $('#sincebar').val();
	var until = $('#untilbar').val();
	
		// VALIDATION
		
		$('#info-header').append('<ul class="nav nav-tabs nav-justified">' + 
		                         '<li id="credentialtab" class="active"><a href="#credentialtab">Credentials</a></li>' +
		                         '<li id="infotab"><a href="#infotab">Search Info</a></li>' +
		                         '</ul>');
		$('#info-header').append('<div id="cred" class="container">' + 
		                         '<h3>Using Credentials:</h3>' +
		                         '<ul><li><strong>Consumer Key: </strong>' + credentials.consumer_key + '</li>' +
		                         '<li><strong>Consumer Secret: </strong>' + credentials.consumer_secret + '</li>' +
		                         '<li><strong>Access Token: </strong>' + credentials.access_token + '</li>' +
		                         '<li><strong>Access Token Secret: </strong>' + credentials.access_token_secret + '</li></ul>' + 
		                         '</div>');

		
		
		// prepare the geosearch parameters
		var geoargs = {
			query: location,
		};
		
		bird.__call(
		            "geo_search",
		            geoargs,
		            function geolookup(reply) {
		            	console.dir(reply);
		            	var location_id = reply.result.places[0].id
		            	
		            	var params = {
		            		// since: since,
		            		// until: until, 
		            		q: keywords + " place:" + location_id,
		            		count: "50"
		            	};		
		            	bird.__call(
		            	            "search_tweets",
		            	            params,
		            	            tweetparse
		            	            );
		            });
		return false;
	});


var templates={ 
	tweet: 
	'<div class="well"><div class="container"><div class="pull-left"><a href="https://www.twitter.com/<%=user.screen_name %>" target="_blank"><img src="<%=user.profile_image_url %>"></a></div><div class="pull-right"><h2><a href="https://www.twitter.com/<%=user.screen_name %>" target="_blank" style="text-decoration:none;color:#<%=user.profile_link_color %>"><%=user.screen_name %></a></h2></div></div><div class="panel panel-default"><div class="panel-body"><h4><%=text %></h4></div></div><div class="container"><div class="row-fluid"><div class="col-md-4"><h4><strong>Tweeted From: </strong><a href="https://twitter.com/search?q=place%3A<%=place.id_str %>" target="_blank" style="text-decoration:none;color:#<%=user.profile_link_color %>"><%=place.full_name %></a></h4></div><div class="col-md-4"><h4><strong>On: </strong><%=created_at %></h4></div><div class="col-md-4"><h4><a href="https://www.twitter.com/<%=user.screen_name %>/status/<%=id_str %>" target="_blank" style="text-decoration:none;color:#<%=user.profile_link_color %>">Original Tweet</a></h4></div></div></div></div>'
};


	//sep
	function tweetparse(reply) {
		console.dir(reply);
		
		$('#content').empty();
		
		var tweets = reply && reply.statuses;
		tweets.forEach(function(response){
			response.created_at = response.created_at.replace(' +0000 2013', '');
			response.place && $('#content').append(_.template(templates.tweet, response));
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