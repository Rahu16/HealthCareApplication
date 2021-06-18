package com.health.boot.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.health.boot.entities.Patient;
import com.health.boot.entities.TestResult;


@Service
public interface ITestResultService 
{

	public TestResult addTestResult(TestResult tr);
	public TestResult updateTestResult(TestResult tr);
	public TestResult removeTestResult(int id);
	public Set<TestResult> viewResultsByPatient(Patient patient);
	public List<TestResult> getAllTestResults();
}
