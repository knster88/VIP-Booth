function submitReservation()
{	
	Spinner.show("Submitting your reservation request...");
	let apigClient = apigClientFactory.newClient();
	
	let body = 
	{
		"name" : document.getElementById( "name" ).value,
		"email" : document.getElementById( "email" ).value,
		"phone" : document.getElementById( "phone" ).value,
		"eventDate" : document.getElementById( "eventDate" ).value,
		"message" : document.getElementById( "message" ).value
	};


	apigClient.reservationsPost({}, body, {})
		.then
		( 
			(result) =>
			{
				window.location = "index.html";
				alert( "We have received your request. We will be in touch soon. Thank you!" )
			}
		)
		.catch
		( 
			(result) => 
			{
				Spinner.hide();
				alert( "We are unable to process your request. Please try again later or contact us directly.  Thank you!" )
			}
		);
}