package com.health.boot.controllers;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("{patientName}/{appointmentId}")
	public String showAppointmentStatus(@PathVariable("patientName") String patientName,@PathVariable("appointmentId") int appointmentId) {
		Patient p=ps.viewPatient(patientName);
		Set<Appointment> set= as.viewAppointments(patientName);
		for(Appointment a:set) {
			if(a.getId()==appointmentId) {
				return "Status is: "+a.getApprovalStatus();
			}
		}
		return "No Appointment Found with id: "+appointmentId +" under Patient Name "+patientName;
	}
	
	@DeleteMapping("{patientName}/{appointmentId}")
	public String cancelAppointment(@PathVariable("patientName") String patientName,@PathVariable("appointmentId") int appointmentId) {
		Patient p=ps.viewPatient(patientName);
		Set<Appointment> set= as.viewAppointments(patientName);
		for(Appointment a:set) {
			if(a.getId()==appointmentId) {
				if(a.getApprovalStatus().equals("approved")) {
					return "Can't Cancel Appointemnet Once It Is Approved";
				}
				else {
					Appointment apt=as.viewAppointment(appointmentId);
					as.removeAppointment(apt);
					return "appointment deleted";
				}
			}
		}
		return "No Appointment Found with id: "+appointmentId +" under Patient Name "+patientName;
	}
}
