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

	// Credential Display
	$('#info-header').append(
	                         '<h3>Using Credentials:</h3>' +
	                         '<ul><li><strong>Consumer Key: </strong>' + credentials.consumer_key + '</li>' +
	                         '<li><strong>Consumer Secret: </strong>' + credentials.consumer_secret + '</li>' +
	                         '<li><strong>Access Token: </strong>' + credentials.access_token + '</li>' +
	                         '<li><strong>Access Token Secret: </strong>' + credentials.access_token_secret + '</li></ul>'                   
	                         );
	
	// Codebird Initialization
	var bird = new Codebird;
	bird.setConsumerKey(credentials.consumer_key, credentials.consumer_secret);
	bird.setToken(credentials.access_token, credentials.access_token_secret);
	

	$('#search-form').submit(function search_submit() {
		var keywords = $('#searchbar').val();
		var location = $('#locationbar').val();
		var since = $('#sincebar').val();
		var until = $('#untilbar').val();
		
		console.dir(since);
		
		// VALIDATION
		
		// prepare the geosearch parameters
		var geoargs = {
			query: location,
		};
		
		bird.__call(
		            "geo_search",
		            geoargs,
		            function geolookup(reply) {
		            	console.dir(reply);
		            	$('#content').append(
		            	                     '<h4>Response from Twitter:</h4>'+
		            	                     '<pre><code> place:' + reply.result.places[0].id + '</code></pre>'
		            	                     );
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


var templates={ tweet: '<div class="well"><div class="container"><div class="pull-left"><a href="https://www.twitter.com/<%=user.screen_name %>" target="_blank"><img src="<%=user.profile_image_url %>"></a></div><div class="pull-right"><h3><%=user.screen_name %></h3></div></div><hr><div class="container"><p><%=text %></p></div><hr><div class="container"><div class="row"><div class="col-md-4"><strong>Tweeted From:</strong><%=place.full_name %></div><div class="col-md-4"><strong>On:</strong><%=created_at %></div><div class="col-md-4"><a href="https://www.twitter.com/<%=user.screen_name %>/status/<%=id_str %>" target="_blank">Original Tweet</a></div></div></div></div>'};


	//sep
	function tweetparse(reply) {
		console.dir(reply);
		
		var tweets = reply && reply.statuses;

		tweets.forEach(function(response){
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