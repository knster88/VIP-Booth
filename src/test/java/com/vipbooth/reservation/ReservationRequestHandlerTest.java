package com.vipbooth.reservation;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ReservationRequestHandlerTest
{

	private static Reservation input;

	@BeforeClass
	public static void createInput() throws IOException
	{
		input = new Reservation();
		input.setName( "name" );
		input.setEmail( "sdadf@sdaf.com" );
		input.setPhone( "123-456-7899" );
		input.setEventDate( "08-08-2018" );
		input.setMessage( "asflaksfakj" );
	}

	private Context createContext()
	{
		TestContext ctx = new TestContext();

		ctx.setFunctionName( "Reservation Request Handler" );

		return ctx;
	}

	@Test
	public void testReservationRequestHandler()
	{
		ReservationRequestHandler handler = new ReservationRequestHandler();
		Context ctx = createContext();

		String output = handler.handleRequest( input, ctx );

		// TODO: validate output here if needed.
		// Assert.assertEquals("Hello from Lambda!", output);
	}
}
