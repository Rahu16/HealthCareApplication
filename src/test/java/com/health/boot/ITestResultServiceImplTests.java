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
public class ITestResultServiceImplTests 
{

	@Autowired
	ITestResultService testService;
	
	@Autowired
	AppointmentRepository ar;
	
	@Autowired
	PatientRepository pr;
	
	//Test for AddTestResult Method
	@Test
	void testAddTestResult()
	{
	Appointment a = ar.findById(123).get();
		TestResult t1=new TestResult();
		t1.setId(4);
		t1.setTestReading(11);
		t1.setCondition("Good");
		t1.setAppointment(a);
		TestResult t2=testService.addTestResult(t1);
		assertTrue(t1.getTestReading() == t2.getTestReading());
		}
	
	//Test for UpdateTestResult Method
	@Test
	void testUpdateTestResult()
	{
		Appointment a = ar.findById(123).get();
		TestResult t1=new TestResult();
		t1.setId(4);
		t1.setTestReading(13);
		t1.setCondition("Good");
		t1.setAppointment(a);
		TestResult t2=testService.updateTestResult(t1);
		assertTrue(t1.getId()==t2.getId());
	}
	
	//Test for RemoveTestResult Method
	@Test
	void testRemoveTestResult()
	{
	TestResult t2=testService.removeTestResult(4);
	assertTrue(t2.getId()==4);
	}
	
	//Test for ViewTestResultByPatient Method
	@Test
	void testViewTestResultByPatient()
	{
		Patient p = pr.findById(100).get();
		Set<TestResult> testResultSet = testService.viewResultsByPatient(p);
		System.out.println(testResultSet);
		assertTrue(testResultSet.size()==2);
	}	
	
	//Test for AllTestResult Method
	@Test
	void testAllTestResult()
	{
		System.out.println(testService.getAllTestResults());
		assertTrue(testService.getAllTestResults().size()==1);
	}	
	
	//Test for TestResultExistException for AddTestResult Method 
	@Test
	void testAddTestResultException() 
	{
		Appointment a = ar.findById(123).get();
		TestResult tt1 = new TestResult();
		tt1.setId(1221);
		tt1.setTestReading(15);
		tt1.setCondition("Bad");
		tt1.setAppointment(a);
		assertThrows(TestResultExistException.class,()->testService.addTestResult(tt1),"testAddTestResultException() should throw exception");
	}
	
	//Test for TestResultNotFoundException for UpdateTestResult Method
	@Test
	void testUpdateTestResultException() 
	{
		Appointment a = ar.findById(123).get();
		TestResult tt1 = new TestResult();
		tt1.setId(12);
		tt1.setTestReading(9);
		tt1.setCondition("not stable");
		tt1.setAppointment(a);
		assertThrows(TestResultNotFoundException.class,()->testService.updateTestResult(tt1),"testUpdateTestResultException() should throw exception");
	}
	
	//Test for TestResultNotFoundException for RemoveTestResult Method
	@Test
	void testRemoveTestResultException() 
	{
		assertThrows(TestResultNotFoundException.class,()->testService.removeTestResult(26),"testRemoveTestResultException() should throw exception");
	}
	
	//Test for PatientFoundException for ViewTestResultByPatient Method
	@Test
	void testViewTestResultByPatientException() 
	{
		Patient p = new Patient(220,"Rahu","8116291022",25,"Male");
		assertThrows(PatientNotFoundException.class,()->testService.viewResultsByPatient(p),"testViewTestResultByPatientException() should throw exception");
	}	
}
