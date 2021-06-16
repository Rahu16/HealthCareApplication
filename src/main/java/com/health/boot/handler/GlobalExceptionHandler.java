package com.health.boot.handler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.health.boot.exceptions.AppointmentExistException;
import com.health.boot.exceptions.AppointmentNotFoundException;
import com.health.boot.exceptions.PatientNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AppointmentExistException.class)
	public ResponseEntity<Object> handleAppointmentExistException(AppointmentExistException ae){
		
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Appointment Already Exist");
		errors.put("Message", ae.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<Object> handleAppointmentNotFoundException(AppointmentNotFoundException an){
		
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Appointment is Not Found");
		errors.put("Message", an.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException pn){
		
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Patient is Not Found");
		errors.put("Message", pn.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}
}