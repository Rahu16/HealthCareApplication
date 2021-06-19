package com.health.boot.services;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.Patient;
import com.health.boot.entities.TestResult;
import com.health.boot.exceptions.PatientNotFoundException;
import com.health.boot.exceptions.TestResultExistException;
import com.health.boot.exceptions.TestResultNotFoundException;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.ITestResultRepository;
import com.health.boot.repository.PatientRepository;


@Service
public class ITestResultServiceImpl implements ITestResultService 
{

	@Autowired
	ITestResultRepository testresultRepo;
	
	@Autowired
	AppointmentRepository ar;
	
	@Autowired
	PatientRepository pr;
	
	
	@Override
	public TestResult addTestResult(TestResult tr) 
	{	
		Optional<TestResult> testR = testresultRepo.findById(tr.getId());
		if(testR.isPresent())
			throw new TestResultExistException("Test Result is Already Exist");
		Appointment a  = ar.findById(tr.getAppointment().getId()).get();
		ar.save(a);
		return testresultRepo.save(tr);
	}

	@Override
	public TestResult updateTestResult(TestResult tr) 
	{
		Optional<TestResult> trOptional =  testresultRepo.findById(tr.getId());
		if(!trOptional.isPresent())
			throw new TestResultNotFoundException("Test Result is Not Found to Update");
		int appointId = trOptional.get().getAppointment().getId();
		Appointment a = ar.findById(appointId).get();
		ar.save(a);
		return testresultRepo.save(tr);	
	}

	@Override
	public TestResult removeTestResult(int id) 
	{
		Optional<TestResult> trOptional =  testresultRepo.findById(id);
		if(trOptional.isEmpty())
			throw new TestResultNotFoundException("Test Result is Not Found to Delete");
		int appointId = trOptional.get().getAppointment().getId();
		Appointment a = ar.findById(appointId).get();
		a.getTestResult().remove(trOptional.get());
//		testresultRepo.deleteById(id);
		ar.save(a);
		return trOptional.get();	
	}

	@Override
	public Set<TestResult> viewResultsByPatient(Patient patient) 
	{
		Set<TestResult> testResultSet = new HashSet<>();
		if(pr.findById(patient.getPatientId()).isEmpty())
			throw new PatientNotFoundException("Patient is Not Found to View All Test Result");
		for(Appointment a : patient.getAppointments()) 
		{
			testResultSet.addAll(a.getTestResult());
		}
		return testResultSet;		
	}

	@Override
	public List<TestResult> getAllTestResults() 
	{
		List<TestResult> list = new ArrayList<>();
		list.addAll(testresultRepo.findAll());
		return list;
	}
}
