package com.health.boot.services;

import java.util.List;

import com.health.boot.entities.Patient;
import com.health.boot.entities.TestResult;
public interface IPatientService {

	Patient registerPatient(Patient patient) throws RuntimeException;
	Patient updatePatientDetails(Patient patient);
	Patient viewPatient(String patientUserName);

	List<TestResult> getAllTestResult(String patientUserName) throws RuntimeException;
	TestResult viewTestResult(int testResultId) throws RuntimeException;
	
}
