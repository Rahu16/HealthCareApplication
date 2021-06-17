package com.health.boot.services;

import java.awt.dnd.DragSourceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.exceptions.DiagnosticCenterAlReadyExistsException;
import com.health.boot.exceptions.DiagnosticCenterNotFoundException;
import com.health.boot.exceptions.DiagnosticTestNotFoundException;
import com.health.boot.repository.AppointmentRepository;
//import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.DiagnosticCenterRepository;
import com.health.boot.repository.DiagnosticTestRepository;

@Service
public class IDiagnosticCenterServiceImpls implements IDiagnosticCenterService {
	@Autowired
	DiagnosticCenterRepository dcr;
	@Autowired
	DiagnosticTestRepository dtr;
	
	@Autowired
	AppointmentRepository ar;
	
	@Override
	public List<DiagnosticCenter> getAllDiagnosticCenters(){
		return dcr.findAll();
	}
	
	@Override
	public DiagnosticCenter createDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws DiagnosticCenterAlReadyExistsException{
		int id=diagnosticCenter.getId();
		Optional<DiagnosticCenter> dc=dcr.findById(id);
		if(dc.isEmpty()) {
			dcr.save(diagnosticCenter);
			return diagnosticCenter;
		}
		else {
			throw new DiagnosticCenterAlReadyExistsException("Diagnostic center with id "+id+" All ready existed");
		}
	}
	
	@Override
	public Optional<DiagnosticCenter> getDiagnosticCenterById(int diagnosticCenterId) {
		Optional<DiagnosticCenter> dc=dcr.findById(diagnosticCenterId);
		if(dc.isEmpty()) {
			throw new DiagnosticCenterNotFoundException("There is no Diagnostic Center with id: "+diagnosticCenterId);
		}
		return dc;
	}
	
	@Override
	public DiagnosticCenter getDiagnosticCenterByName(String centername) {
		ArrayList<DiagnosticCenter> list=(ArrayList<DiagnosticCenter>) dcr.findAll();
		for(DiagnosticCenter dc:list) {
			if(dc.getName().equals(centername)) 
			return dc;
		}
		throw new DiagnosticCenterNotFoundException("Dignostic center is not available with name"+centername);
	}
	
	@Override
	public String removeDiagnosticCenter(int id) throws DiagnosticCenterNotFoundException{
		if(dcr.findById(id).isPresent()) {
			DiagnosticCenter dc=dcr.findById(id).get();
			dcr.delete(dc);
			return "Deleted";
			}
		else
			throw new DiagnosticCenterNotFoundException("Dignostic center is not found with "+id);
	}
	
	@Override
	public DiagnosticCenter addTestInCenter(int testId,int centerId) {
		Optional<DiagnosticTest> dt=dtr.findById(testId);
		Optional<DiagnosticCenter> dc=dcr.findById(centerId);
		if(dc.isEmpty())
			throw new DiagnosticCenterNotFoundException("DiagnosticCenter Not Found With Id: "+centerId);
		if(dt.isEmpty())
			throw new DiagnosticTestNotFoundException("DiagnosticTest Not Found with Id: "+testId);
		
		dc.get().addtest(dt.get());
		dcr.save(dc.get());
		return dc.get();
	}
	
	@Override
	public String updateDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		int id=diagnosticCenter.getId();
		Optional<DiagnosticCenter> dc=dcr.findById(id);
		if(dc.isEmpty()) {
			throw new DiagnosticCenterNotFoundException("Dignostic center is not available with provided details and not possible");}
		dcr.save(diagnosticCenter);
		return "Updated";
	}
	
	@Override
	public DiagnosticTest viewTestDetails(int centerId, String testName) {
		Optional<DiagnosticCenter> dc=dcr.findById(centerId);
		if(dc.isEmpty()) {
			throw new DiagnosticCenterNotFoundException("Center not found with Id: "+centerId);
		}
		DiagnosticCenter dc1=dcr.findById(centerId).get();
		Set<DiagnosticTest> set=dc1.getTests();
		for(DiagnosticTest t:set) {
			if(t.getTestName().equals(testName)) {
				return t;}
		}
		throw new DiagnosticTestNotFoundException("Test not found with name as "+testName+" in the center.");
	}
	
	@Override
	public List<Appointment> getListOfAppointments(String centerName){
		List<Appointment> list=new ArrayList<>();
		List<Appointment> list2=ar.findAll();
		for(Appointment a:list2) {
			String name=a.getDiagnosticCenter().getName();
			if(name==centerName) {
				list.add(a);
			}
		}	
		return list;
	}
	
}

