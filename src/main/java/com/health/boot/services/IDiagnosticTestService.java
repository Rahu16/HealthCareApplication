package com.health.boot.services;

import java.util.List;

import com.health.boot.entities.DiagnosticTest;


/// Interface for all the the service methods
public interface IDiagnosticTestService
{
	public List<DiagnosticTest> getAllTest();
	DiagnosticTest CreateNewTest(DiagnosticTest test);
 	List<DiagnosticTest> getTestsOfDiagnosticCenter(int centerId);
 	DiagnosticTest updateTestDetail(DiagnosticTest test);
 	DiagnosticTest removeTestFromDiagnosticCenter(int centerId, int testId);
 	DiagnosticTest testById(int id);
}
