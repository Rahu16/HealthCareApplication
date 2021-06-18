package com.health.boot.exceptions;

public class AllreadyDiagnosticTestExistException extends RuntimeException
{
	public AllreadyDiagnosticTestExistException(String msg) 
	{
		super(msg);
	}
}
