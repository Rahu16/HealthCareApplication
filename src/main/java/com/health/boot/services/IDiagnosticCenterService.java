package com.health.boot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.exceptions.DiagnosticCenterAlReadyExistsException;
import com.health.boot.exceptions.DiagnosticCenterNotFoundException;

public interface IDiagnosticCenterService 
{

	public List<DiagnosticCenter> getAllDiagnosticCenters();
	public DiagnosticCenter createDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws DiagnosticCenterAlReadyExistsException;
	public String updateDiagnosticCenter(DiagnosticCenter diagnosticCenter);
	public DiagnosticCenter getDiagnosticCenterById(int diagnosticCenterId);
	public String addTestInCenter(int testId,int centerId);
	DiagnosticCenter getDiagnosticCenterByName(String centername);
	String removeDiagnosticCenter(int id) throws DiagnosticCenterNotFoundException;
	DiagnosticTest viewTestDetails(int centerId, String testname);
	List<Appointment> getListOfAppointments(String centerName);
	

}
