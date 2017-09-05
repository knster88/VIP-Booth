function submitReservation()
{
	if( valid() )
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
					alert( "We have received your request. We will be in touch soon.  Thank you!" )
				}
			)
			.catch
			( 
				(result) => 
				{
					alert( "We are unable to process your request.  Please try again later or contact us directly.  Thank you!" )
				}
			);
	}
	
	// Name and Email validation Function.
	function valid() 
	{
		let name = document.getElementById("name").value;
		let email = document.getElementById("email").value;
		let emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		
		let phone = document.getElementById("phone").value;
		let phoneReg = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/;
		
		if( name === '' || email === '' ) 
		{
			alert("Please fill in your name and email.");
			return false;
		} 
		else if( !(email).match(emailReg) ) 
		{
			alert("Please enter a valid email address.");
			return false;
		}
		else if( phone !== '' && !(phone).match(phoneReg) ) 
		{
			alert("Please enter a valid phone number.");
			return false;
		}
		return true;
	}

}