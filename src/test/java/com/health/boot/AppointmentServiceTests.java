package com.health.boot;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.ApprovalStatus;
import com.health.boot.entities.Patient;
import com.health.boot.services.IAppointmentService;
import com.health.boot.exceptions.AppointmentExistException;
import com.health.boot.exceptions.AppointmentNotFoundException;
import com.health.boot.exceptions.PatientNotFoundException;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.PatientRepository;


@SpringBootTest
public class AppointmentServiceTests {
	
	@Autowired
	IAppointmentService as;
	
	@Autowired
	AppointmentRepository ar;
	
	@Autowired
	PatientRepository pr;
	
	@Test
	void testAddAppointment() {
		Appointment a = new Appointment();
		a.setId(300);
		a.setApprovalStatus(ApprovalStatus.approved);
		a.setAppointmentDate(LocalDate.of(2021, 6, 11));
		Patient p  =new Patient(111,"Hasibul","8116282819",22,"Male");
		a.setPatient(p);
		Appointment a2 = as.addAppointment(a);
		assertTrue(a.getId()==a2.getId());
	}
	
	@Test
	void testViewAppointments() {
		
		Patient p = pr.findById(111).get();
		Set<Appointment> set1 = p.getAppointments();
//		for(Appointment a : p.getAppointments()) {
//			System.out.println(a.getId());
//		}
		Set<Appointment> set2 = as.viewAppointments(p.getName());
		equals(set1.equals(set2));
	}
	
	@Test
	void testViewAppointment() {
		Appointment a = ar.findById(200).get();
		Appointment a2 = as.viewAppointment(200);
		assertTrue(a.getId()==a2.getId());
	}
	
	@Test
	void testUpdateAppointment() {
		Appointment a = ar.findById(400).get();
		a.setApprovalStatus(ApprovalStatus.approved);
		Appointment a2 = as.updateAppointment(a);
		assertTrue(a.getApprovalStatus().equals(a2.getApprovalStatus()));
	}
	
	@Test
	void testRemoveAppointment() {
		Appointment a = ar.findById(300).get();
		Appointment a2 = as.removeAppointment(a);
		assertTrue(a.getId()==a2.getId());
	}
	
	@Test
	void testAddAppointmentException() {
		Appointment a = ar.findById(400).get();
		assertThrows(AppointmentExistException.class,()->as.addAppointment(a),"testAddAppointmentException() method should throw Exception");
	}
	
	@Test
	void testRemoveAppointmentException() {
		Appointment a = new Appointment();
		a.setId(300);
		a.setApprovalStatus(ApprovalStatus.approved);
		a.setAppointmentDate(LocalDate.of(2021, 6, 11));
		Patient p  =new Patient(222,"Mokul","8116282819",28,"Male");
		a.setPatient(p);
		assertThrows(AppointmentNotFoundException.class,()->as.removeAppointment(a),"testRemoveAppointmentException() method should throw Exception");
	}
	
	@Test
	void testViewAppointmentsException() {
		String name= "Rahul";
		assertThrows(PatientNotFoundException.class,()->as.viewAppointments(name),"testViewAppointmentsException() method should throw Exception");
	}
	
	@Test
	void testViewAppointmentException() {
		assertThrows(AppointmentNotFoundException.class,()->as.viewAppointment(1000),"testViewAppointmentException() method should throw Exception");
	}
	
	@Test
	void testUpdateAppointmentException() {
		Appointment a = new Appointment();
		a.setId(300);
		a.setApprovalStatus(ApprovalStatus.approved);
		a.setAppointmentDate(LocalDate.of(2021, 6, 11));
		Patient p  =new Patient(222,"Mokul","8116282819",28,"Male");
		a.setPatient(p);
		assertThrows(AppointmentNotFoundException.class,()->as.updateAppointment(a),"testUpdateAppointmentException() method should throw Exception");
	}

}
