$('.input-daterange').datepicker({
    format: "yyyy-mm-dd",
    startDate: "-14d",
    todayBtn: "linked",
    autoclose: true
});

var credentials = 
{
consumer_key: 'sZU3etBMqj7BeHEetZO6w',
consumer_secret: 'rWxPl3oWesD31Gg56SLsXGAcGkyL9ux0LhlAncGK8A',
access_token: '1932368732-nOVLlgcqp5ATBG3VAZrwLopVsuEj3BFysho4cqR',
access_token_secret: 'qThVWaxB7llq1LrS4Ee3mM5iK18n410Jxbzoc4Q2aUo'
};

$('#info-header').append(
    '<h3>Using Credentials:</h3>' +
    '<ul><li><strong>Consumer Key: </strong>' + credentials['consumer_key'] + '</li>' +
    '<li><strong>Consumer Secret: </strong>' + credentials['consumer_secret'] + '</li>' +
    '<li><strong>Access Token: </strong>' + credentials['access_token'] + '</li>' +
    '<li><strong>Access Token Secret: </strong>' + credentials['access_token_secret'] + '</li></ul>'
                            
);
