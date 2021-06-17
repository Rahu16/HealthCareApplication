package com.health.boot.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.ApprovalStatus;
import com.health.boot.entities.Patient;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.PatientRepository;
import com.health.boot.services.IAppointmentService;
import com.health.boot.services.IPatientServiceImpl;


@RestController
@RequestMapping("patient")
public class PatientController {
	

	@Autowired
	IPatientServiceImpl ps;
	
	@Autowired
	IAppointmentService as;
	
	@Autowired
	PatientRepository pr;
	


	@GetMapping("/{patientName}/{date}/requestAppointment")
	public Appointment requestAppoinment(@PathVariable("patientName") String patientName,@PathVariable("date") String date) {
		System.out.println("Hello Everyone");
		LocalDate Date = LocalDate.parse(date);
		Patient p = ps.viewPatient(patientName);
		Appointment a = new Appointment();
		a.setId(120);
		a.setAppointmentDate(Date);
		a.setApprovalStatus(ApprovalStatus.pending);
		a.setPatient(p);
		return as.addAppointment(a);
	}

}
