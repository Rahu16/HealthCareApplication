package com.health.boot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.Patient;
import com.health.boot.entities.TestResult;
import com.health.boot.exceptions.PatientExistException;
import com.health.boot.exceptions.PatientNotFoundException;
import com.health.boot.repository.PatientRepository;

@Service
public class  IPatientServiceImpl implements IPatientService
{
	
	@Autowired
	PatientRepository pr;

	
	@Override
	public Patient registerPatient(Patient patient) throws RuntimeException 
	{
		if(pr.findById(patient.getPatientId()).isPresent())
			throw new PatientExistException("Patient is already exist with the same Id");
		return pr.save(patient);
	}

	@Override
	public Patient updatePatientDetails(Patient patient) 
	{
		if(!pr.findById(patient.getPatientId()).isPresent())
			throw new PatientNotFoundException("Patient is Not Found to Update");
		return pr.save(patient);
	}

	@Override
	public Patient viewPatient(String patientUserName) 
	{
		Patient p = pr.findPatientByName(patientUserName);
		if(p==null)
			throw new PatientNotFoundException("Patient is Not Found TO View it.");
		return p;
	}
	
	public Patient viewPatient(int patientId) 
	{		
		Optional<Patient> p = pr.findById(patientId);
		if(p.isEmpty())
			throw new PatientNotFoundException("Patient is Not Found TO View it.");
		return p.get();
	}
	
	public Patient removePatient(int pid) 
	{
		Patient p = viewPatient(pid);
		pr.deleteById(pid);
		return p;
	}

	@Override
	public List<TestResult> getAllTestResult(String patientUserName) throws RuntimeException {
		List<TestResult> list = new ArrayList<>();
		Patient p = pr.findPatientByName(patientUserName);
		if(p==null)
			throw new PatientNotFoundException("Patient is Not Found to see the Test Results");
		for(Appointment a : p.getAppointments())
			list.addAll(a.getTestResult().stream().collect(Collectors.toList()));
		return list;
	}

	@Override
	public TestResult viewTestResult(int testResultId) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
