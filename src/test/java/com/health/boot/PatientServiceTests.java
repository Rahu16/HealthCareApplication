package com.health.boot;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.health.boot.entities.Patient;
import com.health.boot.exceptions.PatientNotFoundException;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.PatientRepository;
import com.health.boot.exceptions.PatientExistException;
import com.health.boot.services.IPatientService;

@SpringBootTest
public class PatientServiceTests 
{
	
	@Autowired
	IPatientService ps;
	
	@Autowired
	PatientRepository pr;
	
	/// Testing whether the method is creating new patient or not
	@Test
	void testRegisterPatient() 
	{
		Patient p  =new Patient(999,"Azhar","8133071345",30,"Male");
		Patient p1 = ps.registerPatient(p);
		assertTrue(p.getPatientId()==p1.getPatientId());
	}
	
	
	/// Testing whether the method is throwing PatientExistException, if username is already is existed
	@Test
	void testRegisterPatientExistException() 
	{
		Patient p  =new Patient(111,"Hasibul","8116282819",22,"Male");
		assertThrows(PatientExistException.class,()-> ps.registerPatient(p),"testRegisterPatientExisttException() should throw Exception");
	}
	
	/// Testing whether the method is updating patient details.
	@Test
	void testUpdatePatient() 
	{
		Patient p  =new Patient(444,"Sonia","7003071345",25,"Female");
		Patient p1 = ps.updatePatientDetails(p);
		assertTrue(p.getName().equals(p1.getName()));
	}
	
	
	/// Testing whether the method is throwing PatientNotFoundException, if wrong username is given as argument
	@Test
	void testUpdatePatientNotFoundException() 
	{
		Patient p  =new Patient(100,"Hasibul","8116282819",22,"Male");
		assertThrows(PatientNotFoundException.class,()-> ps.updatePatientDetails(p),"testUpdatePatientNotFoundException() should throw Exception");
	}
	
	
	/// Testing whether the method is returning patient details or not
	@Test
	void testViewPatient() 
	{
		Patient p  =pr.findById(111).get();
		Patient p1 = ps.viewPatient(p.getName());
		assertTrue(p.getPatientId()==p1.getPatientId());
	}
	
	
	/// Testing whether the method is throwing PatientNotFoundException or not, if unregister patient details is given as argument.
	@Test
	void testViewPatientNotFoundException() 
	{
		assertThrows(PatientNotFoundException.class,()-> ps.viewPatient("Rohita Singh"),"testViewPatientNotFoundException() should throw Exception");
	}
}
