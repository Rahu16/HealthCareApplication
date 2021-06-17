package com.health.boot.service;

import java.util.List;

import com.health.boot.entities.DiagnosticTest;

public interface IDiagnosticTestService {
	public List<DiagnosticTest> getAllTest();
	DiagnosticTest CreateNewTest(DiagnosticTest test);
 	List<DiagnosticTest> getTestsOfDiagnosticCenter(int centerId);
 	DiagnosticTest updateTestDetail(DiagnosticTest test);
 	DiagnosticTest removeTestFromDiagnosticCenter(int centerId, int testId);
 	DiagnosticTest testById(int id);
}
