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
import com.health.boot.entities.Patient;
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
public class AdminController
{
	
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
	public ResponseEntity<DiagnosticCenter> createNewDiagnosticCenter(@RequestBody DiagnosticCenter dc)
	{
		return new ResponseEntity<DiagnosticCenter>(dcs.createDiagnosticCenter(dc),HttpStatus.CREATED);
	}
	
	
	@PostMapping("/createNewTest")
	public ResponseEntity<DiagnosticTest> createNewTest(@RequestBody DiagnosticTest t) 
	{
		return new ResponseEntity<DiagnosticTest>(dts.CreateNewTest(t),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/updateTestDetails")
	public ResponseEntity<DiagnosticTest> updateTestDetails(@RequestBody DiagnosticTest test)
	{
		return new ResponseEntity<DiagnosticTest>(dts.updateTestDetail(test),HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/updateCenterDetails")
	public ResponseEntity<String> updateCenterDetails(@RequestBody DiagnosticCenter dc)
	{
		return new ResponseEntity<String>(dcs.updateDiagnosticCenter(dc),HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/addTestInCenter/{tid}/{cid}")
	public ResponseEntity<String> addTestInCenter(@PathVariable("cid") int cid,@PathVariable("tid") int tid)
	{
		return new ResponseEntity<String>(dcs.addTestInCenter(tid, cid),HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/delTestFromCenter/{cid}/{tid}")
	public ResponseEntity<DiagnosticTest> delTestFromCenter(@PathVariable("cid") int cid,@PathVariable("tid") int tid)
	{
		return new ResponseEntity<DiagnosticTest>(dts.removeTestFromDiagnosticCenter(cid, tid),HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/deleteDiagnosticCenter/{id}")
	public ResponseEntity<String> deleteDiagnosticCenter(@PathVariable("id") int id) 
	{
		return new ResponseEntity<String>(dcs.removeDiagnosticCenter(id),HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/changeAppointmentStatus/{appointId}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable("appointId") int appointId, @PathVariable("status") ApprovalStatus status)
	{
		Appointment a = asi.viewAppointment(appointId);
		a.setApprovalStatus(status);
		asi.updateAppointment(a);
		return new ResponseEntity<String>("Status is Changed",HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getalltestResults")
	public ResponseEntity<List> getAllTestResults()
	{
		return new ResponseEntity<List>(trs.getAllTestResults(),HttpStatus.FOUND);
	}
	
	
	@PostMapping("/addTestResultToAppointment")
	public ResponseEntity<String> addTestToAppointment(@RequestBody addTestResultObjHolder obj)
	{
		Appointment a = asi.viewAppointment(obj.getAppointId());
		TestResult tr = new TestResult();
		tr.setId(obj.getId());
		tr.setTestReading(obj.getTestReading());
		tr.setCondition(obj.getCondition());
		tr.setAppointment(a);
		trs.addTestResult(tr);
//		a.getTestResult().add(tr);
		System.out.println("hello");
//		asi.updateAppointment(a);
		return new ResponseEntity<String>("Test result is added to the Appointment",HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getAppointmentList/{status}")
	public ResponseEntity<List> getAppointmentsByStatus(@PathVariable("status") ApprovalStatus status)
	{
		List<Appointment> appointmentList = ar.findAll();
		return new ResponseEntity<List>(appointmentList.stream().filter(a->a.getApprovalStatus().equals(status)).collect(Collectors.toList()),HttpStatus.FOUND);
	}
	
	
	@GetMapping("/getAllPatient")
	public ResponseEntity<List> getAllPatient()
	{
		return new ResponseEntity<List>(pr.findAll(),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/removePatient/{patientId}")
	public ResponseEntity<String> deletePatient(@PathVariable("patientId") int id)
	{
		psi.removePatient(id);
		return new ResponseEntity<String>("Patient is Deleted",HttpStatus.ACCEPTED);
	
	}
	
	
	@DeleteMapping("/removeAppointment/{appointId}")
	public ResponseEntity<String> deleteAppointment(@PathVariable("appointId") int id)
	{
		asi.removeAppointment(asi.viewAppointment(id));
		return new ResponseEntity<String>("Appointment is Deleted",HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/removeTestResult/{testId}")
	public ResponseEntity<String> delete(@PathVariable("testId") int id)
	{
		trs.removeTestResult(id);
		return new ResponseEntity<String>("Test Result is Deleted",HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/show/{id}")
	public ResponseEntity<Patient> seePatient(@PathVariable("id") int id)
	{
		return new ResponseEntity<Patient>(psi.viewPatient(id),HttpStatus.ACCEPTED);
	}
	
}