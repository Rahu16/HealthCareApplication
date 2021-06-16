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
import com.health.boot.exceptions.DiagnosticCenterAlReadyExistsException;
import com.health.boot.exceptions.DiagnosticCenterNotFoundException;
import com.health.boot.exceptions.PatientExistException;
import com.health.boot.exceptions.PatientNotFoundException;

import com.health.boot.exceptions.UserNotFoundException;
import com.health.boot.exceptions.UserAlreadyExistException;
import com.health.boot.exceptions.UserIdPasswordInvalidException;




@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(UserNotFoundException ux){
		
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Not Found");
		errors.put("Message", ux.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(UserAlreadyExistException ux){
		
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "User Already Exist");
		errors.put("Message", ux.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserIdPasswordInvalidException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(UserIdPasswordInvalidException uv){
		
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Password is not matching");
		errors.put("Message", uv.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
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
	 @ExceptionHandler(PatientExistException.class)
	    public ResponseEntity<Object> handlePatientExistException(PatientExistException pe){
	       
	        Map<String,Object> errors = new LinkedHashMap<>();
	       
	        errors.put("Error", "Patient Already Exist");
	        errors.put("Message", pe.getMessage());
	        errors.put("Time", LocalDateTime.now());
	       
	        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	    }
	 
		@ExceptionHandler(DiagnosticCenterNotFoundException.class)
		public ResponseEntity<Object> handleDiagnosticCenterException(DiagnosticCenterNotFoundException dc){
			
			Map<String,Object> errors = new LinkedHashMap<>();
			
			errors.put("Error", "There is No DiagnosticCenter Found");
			errors.put("Message", dc.getMessage());
			errors.put("Time", LocalDateTime.now());
			
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(DiagnosticCenterAlReadyExistsException.class)
		public ResponseEntity<Object> handleDiagnosticCenterException(DiagnosticCenterAlReadyExistsException de){
			
			Map<String,Object> errors = new LinkedHashMap<>();
			
			errors.put("Error", "There is Already a DiagnosticCenter");
			errors.put("Message", de.getMessage());
			errors.put("Time", LocalDateTime.now());
			
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
			
		}
}
