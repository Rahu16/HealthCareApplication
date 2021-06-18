package com.health.boot.exceptions;

public class UserNotFoundException extends RuntimeException
{

	public UserNotFoundException(String message) 
	{
		super(message);
		}
	}
