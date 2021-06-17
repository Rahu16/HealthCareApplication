package com.health.boot.controllers;

import java.time.LocalDate;
import java.util.HashSet;
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
import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.entities.Patient;
import com.health.boot.exceptions.AppointmentNotFoundException;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.PatientRepository;
import com.health.boot.services.IAppointmentService;
import com.health.boot.services.IDiagnosticCenterServiceImpls;
import com.health.boot.services.IDiagnosticTestServiceImpls;
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
	
	@Autowired
	IDiagnosticCenterServiceImpls dcs;
	
	@Autowired
	IDiagnosticTestServiceImpls dts;
	

	@PostMapping("/registerPatient")
	public ResponseEntity<String> registerPatient(@RequestBody Patient p) {
		ps.registerPatient(p);
		return new ResponseEntity<String>("Resgistered Sucessfully",HttpStatus.ACCEPTED);
	}
	

	@PostMapping("/requestAppointment")
	public ResponseEntity<Appointment> requestAppoinment(@RequestBody ObjHolderRequestAppointment requestAppoint) {
		System.out.println("Hello Everyone");
		Set<DiagnosticTest> set = new HashSet<>();
		Patient p = ps.viewPatient(requestAppoint.getPatientName());
		DiagnosticCenter dc = dcs.getDiagnosticCenterById(requestAppoint.getDiagCenterId()).get();
		DiagnosticTest dt = dcs.viewTestDetails(requestAppoint.getDiagCenterId(), requestAppoint.getTestName());
		Appointment a = new Appointment();
		a.setId(requestAppoint.getAppointId());
		a.setAppointmentDate(requestAppoint.getDate());
		a.setApprovalStatus(ApprovalStatus.pending);
		a.setPatient(p);
		a.setDiagnosticCenter(dc);
//		a.getDiagnosticTests().add(dt);
		set.add(dt);
		a.setDiagnosticTests(set);
		return new ResponseEntity<Appointment>(as.addAppointment(a),HttpStatus.CREATED);
	}

<<<<<<< HEAD
	@GetMapping("/{patientName}/{appointmentId}/showstatus")
	public ResponseEntity<String> showAppointmentStatus(@PathVariable("patientName") String patientName,@PathVariable("appointmentId") int appointmentId) {
		ps.viewPatient(patientName);
=======
	@GetMapping("{patientName}/{appointmentId}")
	public String showAppointmentStatus(@PathVariable("patientName") String patientName,@PathVariable("appointmentId") int appointmentId) {
		Patient p=ps.viewPatient(patientName);
>>>>>>> 25bf11f227864dea7344bfaedaa3f70e6f47a518
		Set<Appointment> set= as.viewAppointments(patientName);
		for(Appointment a:set) {
			if(a.getId()==appointmentId) {
				return new ResponseEntity<String>("Status is: "+a.getApprovalStatus(),HttpStatus.FOUND);
			}
		}
<<<<<<< HEAD
		throw new AppointmentNotFoundException("Appointment is Not Found to See the Status");
=======
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
>>>>>>> 25bf11f227864dea7344bfaedaa3f70e6f47a518
	}
}
