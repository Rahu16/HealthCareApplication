package com.health.boot;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.Patient;
import com.health.boot.entities.TestResult;
import com.health.boot.exceptions.TestResultExistException;
import com.health.boot.exceptions.TestResultNotFoundException;
import com.health.boot.exceptions.PatientNotFoundException;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.PatientRepository;
import com.health.boot.services.ITestResultService;

@SpringBootTest
public class ITestResultServiceImplTests {

	@Autowired
	ITestResultService testService;
	
	@Autowired
	AppointmentRepository ar;
	
	@Autowired
	PatientRepository pr;
	
	@Test
	void testAddTestResult()
	{
	Appointment a = ar.findById(222).get();
		TestResult t1=new TestResult();
		t1.setId(4);
		t1.setTestReading(11);
		t1.setCondition("Good");
		t1.setAppointment(a);
		TestResult t2=testService.addTestResult(t1);
		assertTrue(t1.getTestReading() == t2.getTestReading());
		}
	
	@Test
	void testUpdateTestResult()
	{
		Appointment a = ar.findById(111).get();
		TestResult t1=new TestResult();
		t1.setId(5);
		t1.setTestReading(13);
		t1.setCondition("Good");
		t1.setAppointment(a);
		TestResult t2=testService.updateTestResult(t1);
		assertTrue(t1.getId()==t2.getId());
	}

	@Test
	void testRemoveTestResult()
	{
	TestResult t2=testService.removeTestResult(12);
	assertTrue(t2.getId()==12);
	}
	
	@Test
	void testViewTestResultByPatient()
	{
		Patient p = pr.findById(100).get();
		Set<TestResult> testResultSet = testService.viewResultsByPatient(p);
		System.out.println(testResultSet);
		assertTrue(testResultSet.size()==5);
	}	
	
	@Test
	void testAllTestResult()
	{
		assertTrue(testService.getAllTestResults().size()==5);
	}	
	
	@Test
	void testAddTestResultException() {
		Appointment a = ar.findById(111).get();
		TestResult tt1 = new TestResult();
		tt1.setId(5);
		tt1.setTestReading(09.00);
		tt1.setCondition("not stable");
		tt1.setAppointment(a);
		assertThrows(TestResultExistException.class,()->testService.addTestResult(tt1),"testAddTestResultException() should throw exception");
	}
	
	@Test
	void testUpdateTestResultException() {
		Appointment a = ar.findById(111).get();
		TestResult tt1 = new TestResult();
		tt1.setId(30);
		tt1.setTestReading(09.00);
		tt1.setCondition("not stable");
		tt1.setAppointment(a);
		assertThrows(TestResultNotFoundException.class,()->testService.updateTestResult(tt1),"testUpdateTestResultException() should throw exception");
	}
	
	@Test
	void testRemoveTestResultException() {
		assertThrows(TestResultNotFoundException.class,()->testService.removeTestResult(26),"testRemoveTestResultException() should throw exception");
	}
	
	@Test
	void testViewTestResultByPatientException() {
		Patient p = pr.findById(200).get();
		assertThrows(PatientNotFoundException.class,()->testService.viewResultsByPatient(p),"testViewTestResultByPatientException() should throw exception");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
