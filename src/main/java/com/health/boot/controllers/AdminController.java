package com.health.boot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.ApprovalStatus;
import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.entities.TestResult;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.PatientRepository;
import com.health.boot.services.IAppointmentServiceImpl;
import com.health.boot.services.IDiagnosticCenterService;
import com.health.boot.services.IDiagnosticTestService;
import com.health.boot.services.IPatientServiceImpl;
import com.health.boot.services.ITestResultServiceImpl;

@RestController
@RequestMapping("/Admin")
public class AdminController {
	
	@Autowired
	IPatientServiceImpl psi;

	@Autowired
	IAppointmentServiceImpl asi;
	
	@Autowired
	IDiagnosticCenterService dcs;
	
	@Autowired
	IDiagnosticTestService dts;
	
	@Autowired
	ITestResultServiceImpl trs;
	
	@Autowired
	AppointmentRepository ar;
	
	@Autowired
	PatientRepository pr;
	
	
	@PostMapping("/newDiagnosticCenter")
	public DiagnosticCenter createNewDiagnosticCenter(@RequestBody DiagnosticCenter dc) {
		dcs.createDiagnosticCenter(dc);
		return dc;
	}
	
	@PostMapping("/createNewTest")
	public DiagnosticTest createNewTest(@RequestBody DiagnosticTest t) {
		return dts.CreateNewTest(t);
	}
		
	@PutMapping("/updateTestDetails")
	public DiagnosticTest updateTestDetails(@RequestBody DiagnosticTest test) {
		return dts.updateTestDetail(test);
	}
	
	@PutMapping("/updateCenterDetails")
	public String updateCenterDetails(@RequestBody DiagnosticCenter dc) {
		return dcs.updateDiagnosticCenter(dc);
	}
	
	@PutMapping("/addTestInCenter/{tid}/{cid}")
	public String addTestInCenter(@PathVariable("cid") int cid,@PathVariable("tid") int tid) {
		DiagnosticCenter dm=dcs.addTestInCenter(tid, cid);
		if(dm==null) {
			return "not added";
		}
		return"added";
	}
	
	@PutMapping("/delTestFromCenter/{cid}/{tid}")
	public DiagnosticTest delTestFromCenter(@PathVariable("cid") int cid,@PathVariable("tid") int tid) {
		return dts.removeTestFromDiagnosticCenter(cid, tid);
	}
	
	@DeleteMapping("/deleteDiagnosticCenter/{id}")
	public String deleteDiagnosticCenter(@PathVariable("id") int id) {
		return dcs.removeDiagnosticCenter(id);
	}
	
	@PutMapping("/changestatus/{appointId}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable("appointId") int appointId, @PathVariable("status") ApprovalStatus status) {
		Appointment a = asi.viewAppointment(appointId);
		a.setApprovalStatus(status);
		asi.updateAppointment(a);
		return new ResponseEntity<String>("Status is Changed",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getalltestResults")
	public ResponseEntity<List> getAllTestResults(){
		return new ResponseEntity<List>(trs.getAllTestResults(),HttpStatus.FOUND);
	}
	
	@PostMapping("/{appointId}/addTestResult")
	public ResponseEntity<String> addTestToAppointment(@PathVariable("appointId") int appointId, @RequestBody TestResult tr) {
		Appointment a = asi.viewAppointment(appointId);
		a.getTestResult().add(tr);
		asi.updateAppointment(a);
		return new ResponseEntity<String>("Test result is added to the Appointment",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAppointmentList/{status}")
	public ResponseEntity<List> getAppointmentsByStatus(@PathVariable("status") ApprovalStatus status){
		List<Appointment> appointmentList = ar.findAll();
		return new ResponseEntity<List>(appointmentList.stream().filter(a->a.getApprovalStatus().equals(status)).collect(Collectors.toList()),HttpStatus.FOUND);
	}
	
	@GetMapping("/getAllPatient")
	public ResponseEntity<List> getAllPatient(){
		return new ResponseEntity<List>(pr.findAll(),HttpStatus.OK);
	}
	
//	@DeleteMapping("/removePatient/{patientId}")
//	public ResponseEntity<String> removePatient(){
//		ps
//	
//	}

	
	
}