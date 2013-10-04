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
	





	// Wrap in func submit
	var params = {
		query: "Toronto"
	};
	bird.__call(
	            "geo_search",
	            params,
	            geolookup
	            );
	
	//keep sep
	function geolookup(reply) {
		console.dir(reply);
		$('#content').append(
		                     '<h4>Response from Twitter:</h4>'+
		                     '<pre><code>' + reply.result.places[0].id + '</code></pre>'
		                     );
		var placeid = '"' + reply.result.places[0].id + '"'
		
		var params = {
			place: placeid,
			since: "2013-09-25",
			until: "2013-10-02", 
			q: "happy",
			count: "50"
		};		
		bird.__call(
		            "search_tweets",
		            params,
		            tweetparse
		            );
	};
	
	//wrap in submit? or callbacking?


	
	
	//sep
	function tweetparse(reply) {
		console.dir(reply);
		_.each(reply.statuses, function printstatus(element, index, list) { 
			$('#content').append(
			                     '<h4>Response from Twitter:</h4>'+
			                     '<pre><code>' + index + ': ' + element.text + '<br/>' + 
			                     'place: ' + element.place + '</code></pre>'
			                     );
		});
	};


});




