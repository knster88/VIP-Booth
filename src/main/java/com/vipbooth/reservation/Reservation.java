package com.vipbooth.reservation;

public class Reservation
{

	private String name;
	private String email;
	private String phone;
	private String eventDate;
	private String message;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone( String phone )
	{
		this.phone = phone;
	}

	public String getEventDate()
	{
		return eventDate;
	}

	public void setEventDate( String eventDate )
	{
		this.eventDate = eventDate;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}
	
	@Override
	public String toString()
	{
		return "Reservation [name=" + name + ", email=" + email + ", phone=" + phone
				+ ", eventDate=" + eventDate + ", message=" + message + "]";
	}


}
