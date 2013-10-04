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
	

	
	
	//sep
	function tweetparse(reply) {
		console.dir(reply);
		_.each(reply.statuses, function printstatus(element, index, list) { 
			element.place && $('#content').append(
			                     '<div class="well"><div class="container"><h4 class="pull-left">' + element.user.screen_name + ':</h4>' +
			                     '<img src="' + element.user.profile_image_url + '" class="pull-right"></div>' +
			                     '<pre><code>' + index + ': ' + element.text + '<br/>' + 
			                     'place: ' + element.place.full_name + '</code></pre></div>'
			                     );
		});
	};


});




