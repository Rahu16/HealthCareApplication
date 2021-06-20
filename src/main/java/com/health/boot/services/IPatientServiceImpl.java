package com.health.boot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	/// This method is used to register a new patient
	@Override
	public Patient registerPatient(Patient patient) throws RuntimeException 
	{
		if(pr.findById(patient.getPatientId()).isPresent())
			throw new PatientExistException("Patient is already exist with the same Id");
		return pr.save(patient);
	}
	
	
	/// This method is used to update the patient details
	@Override
	public Patient updatePatientDetails(Patient patient) 
	{
		if(!pr.findById(patient.getPatientId()).isPresent())
			throw new PatientNotFoundException("Patient is Not Found to Update");
		return pr.save(patient);
	}
	
	
	/// This method returns patients by giving argument as patient userName.
	@Override
	public Patient viewPatient(String patientUserName) 
	{
		Patient p = pr.findPatientByName(patientUserName);
		if(p==null)
			throw new PatientNotFoundException("Patient is Not Found TO View it.");
		return p;
	}
	
	
	/// This method returns patients by giving argument as patient Id
	public Patient viewPatient(int patientId) 
	{		
		Optional<Patient> p = pr.findById(patientId);
		if(p.isEmpty())
			throw new PatientNotFoundException("Patient is Not Found TO View it.");
		return p.get();
	}
	
	
	/// This method is used to delete patient by giving patient id as argument.
	public Patient removePatient(int pid) 
	{
		Patient p = viewPatient(pid);
		pr.deleteById(pid);
		return p;
	}


}
