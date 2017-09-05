package com.vipbooth.reservation;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class ReservationRequestHandler implements RequestHandler<Reservation, String>
{
	private static LambdaLogger LOGGER;
	private static final String FROM = "reservation@thevipbooth.com";
	private static final String[] TO = new String[]
	{ "reservation@thevipbooth.com", "vipbooth2013@gmail.com" };
	private static final String SUBJECT = "VIP Booth Reservation";

	@Override
	public String handleRequest( Reservation input, Context context )
	{
		LOGGER = context.getLogger();

		SendEmailRequest email = this.constructEmailRequest( input );

		return sendEmail( email );
	}

	private String sendEmail( SendEmailRequest request )
	{
		try
		{
			LOGGER.log( "Sending email to " + request.getDestination().getToAddresses() );

			AmazonSimpleEmailService emailService = AmazonSimpleEmailServiceClientBuilder
					.standard().withRegion( Regions.US_EAST_1 ).build();

			emailService.sendEmail( request );

			LOGGER.log( "Email sent!" );
			return "Reservation request sent.";

		}
		catch( Exception ex )
		{

			LOGGER.log( "Error sending email.  Error: " + ex.getMessage() );
			return "Reservation request failed to send.";
		}
	}

	private SendEmailRequest constructEmailRequest( Reservation input )
	{
		Destination destination = new Destination().withToAddresses( TO );

		Content textBody = this.constructEmailBody( input );

		Message message = new Message( new Content( SUBJECT ), new Body( textBody ) );

		return new SendEmailRequest().withSource( FROM ).withDestination( destination )
				.withMessage( message );
	}

	private Content constructEmailBody( Reservation input )
	{
		String body = "Reservation Request Details: \r\n  " + " Client Name: "
				+ input.getName() + "\r\n  " + " Client Email: " + input.getEmail()
				+ "\r\n  " + " Client Phone: " + input.getPhone() + "\r\n  "
				+ " Event Date: " + input.getEventDate() + "\r\n  "
				+ " Additional Notes: " + input.getMessage() + "\r\n";

		return new Content( body );
	}

}
