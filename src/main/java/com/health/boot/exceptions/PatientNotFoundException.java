package com.health.boot.exceptions;

public class PatientNotFoundException extends RuntimeException
{

	public PatientNotFoundException(String message) 
	{
		super(message);
		}
	}
