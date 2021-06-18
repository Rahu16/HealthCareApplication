package com.health.boot.exceptions;

public class AppointmentExistException extends RuntimeException
{

	public AppointmentExistException(String message) 
	{
		super(message);
		}
	}
