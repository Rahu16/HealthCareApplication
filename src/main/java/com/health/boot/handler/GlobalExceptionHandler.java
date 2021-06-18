package com.health.boot.handler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.health.boot.exceptions.AllreadyDiagnosticTestExistException;
import com.health.boot.exceptions.AppointmentExistException;
import com.health.boot.exceptions.AppointmentNotFoundException;
import com.health.boot.exceptions.DiagnosticCenterAlReadyExistsException;
import com.health.boot.exceptions.DiagnosticCenterNotFoundException;
import com.health.boot.exceptions.DiagnosticTestNotFoundException;
import com.health.boot.exceptions.PatientExistException;
import com.health.boot.exceptions.PatientNotFoundException;
import com.health.boot.exceptions.TestResultExistException;
import com.health.boot.exceptions.TestResultNotFoundException;
import com.health.boot.exceptions.UserNotFoundException;
import com.health.boot.exceptions.UserAlreadyExistException;
import com.health.boot.exceptions.UserIdPasswordInvalidException;




@ControllerAdvice
public class GlobalExceptionHandler 
{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		List<String> errors = ex.getBindingResult()
				                .getFieldErrors()
				                .stream()
				                .map(x -> x.getDefaultMessage())
				                .collect(Collectors.toList());
	
		Map<String,Object> errorMap=new LinkedHashMap<>();
		errorMap.put("ValidationErrors", errors);
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(UserNotFoundException ux)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Not Found");
		errors.put("Message", ux.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(UserAlreadyExistException ux)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "User Already Exist");
		errors.put("Message", ux.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(UserIdPasswordInvalidException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(UserIdPasswordInvalidException uv)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Password is not matching");
		errors.put("Message", uv.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(AppointmentExistException.class)
	public ResponseEntity<Object> handleAppointmentExistException(AppointmentExistException ae)
	{
	    Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Appointment Already Exist");
		errors.put("Message", ae.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<Object> handleAppointmentNotFoundException(AppointmentNotFoundException an)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Appointment is Not Found");
		errors.put("Message", an.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException pn)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Patient is Not Found");
		errors.put("Message", pn.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PatientExistException.class)
	    public ResponseEntity<Object> handlePatientExistException(PatientExistException pe)
	{
	        Map<String,Object> errors = new LinkedHashMap<>();
	       
	        errors.put("Error", "Patient Already Exist");
	        errors.put("Message", pe.getMessage());
	        errors.put("Time", LocalDateTime.now());
	       
	        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	    
	}
	 
		@ExceptionHandler(DiagnosticCenterNotFoundException.class)
		public ResponseEntity<Object> handleDiagnosticCenterException(DiagnosticCenterNotFoundException dc)
		{
			Map<String,Object> errors = new LinkedHashMap<>();
			
			errors.put("Error", "There is No DiagnosticCenter Found");
			errors.put("Message", dc.getMessage());
			errors.put("Time", LocalDateTime.now());
			
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
		
		}
		
		@ExceptionHandler(DiagnosticCenterAlReadyExistsException.class)
		public ResponseEntity<Object> handleDiagnosticCenterException(DiagnosticCenterAlReadyExistsException de)
		{
			Map<String,Object> errors = new LinkedHashMap<>();
			
			errors.put("Error", "There is Already a DiagnosticCenter");
			errors.put("Message", de.getMessage());
			errors.put("Time", LocalDateTime.now());
			
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
			
		}

	@ExceptionHandler(AllreadyDiagnosticTestExistException.class)
	public ResponseEntity<Object> handleDiagnosticTestException(AllreadyDiagnosticTestExistException ad)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "There is allready a DiagnosticTest");
		errors.put("Message", ad.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DiagnosticTestNotFoundException.class)
	public ResponseEntity<Object> handleDiagnosticTestException(DiagnosticTestNotFoundException dn)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "There is No DiagnosticTest Found");
		errors.put("Message", dn.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TestResultExistException.class)
	public ResponseEntity<Object> handleTestResultException(TestResultExistException tr)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "test result exists");
		errors.put("Message", tr.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TestResultNotFoundException.class)
	public ResponseEntity<Object> handleTestResultNotFoundException(TestResultNotFoundException te)
	{
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "test result Not Found");
		errors.put("Message", te.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}
	}
