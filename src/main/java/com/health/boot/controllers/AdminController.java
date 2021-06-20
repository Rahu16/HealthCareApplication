package com.health.boot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
	
	
	/// This controller method is responsible for creating a new Diagnostic Center
	@PostMapping("/newDiagnosticCenter")
	public ResponseEntity<DiagnosticCenter> createNewDiagnosticCenter(@Valid @RequestBody DiagnosticCenter dc)
	{
		return new ResponseEntity<DiagnosticCenter>(dcs.createDiagnosticCenter(dc),HttpStatus.CREATED);
	}
	
	
	@PostMapping("/createNewTest")
	public ResponseEntity<DiagnosticTest> createNewTest(@Valid @RequestBody DiagnosticTest t) 
	{
		return new ResponseEntity<DiagnosticTest>(dts.CreateNewTest(t),HttpStatus.CREATED);
	}
	
	
	/// This method is responsible for Update the Test Details of a Diagnostic Test
	@PutMapping("/updateTestDetails")
	public ResponseEntity<DiagnosticTest> updateTestDetails(@Valid @RequestBody DiagnosticTest test)
	{
		return new ResponseEntity<DiagnosticTest>(dts.updateTestDetail(test),HttpStatus.ACCEPTED);
	}
	
	
	/// This method is responsible for updating the center details of a Diagnostic Center
	@PutMapping("/updateCenterDetails")
	public ResponseEntity<String> updateCenterDetails(@Valid @RequestBody DiagnosticCenter dc)
	{
		return new ResponseEntity<String>(dcs.updateDiagnosticCenter(dc),HttpStatus.ACCEPTED);
	}
	
	
	/// This method is responsible for adding a Diagnostic Test in a Diagnostic Center
	@PutMapping("/addTestInCenter/{tid}/{cid}")
	public ResponseEntity<String> addTestInCenter(@PathVariable("cid") int cid,@PathVariable("tid") int tid)
	{
		return new ResponseEntity<String>(dcs.addTestInCenter(tid, cid),HttpStatus.ACCEPTED);
	}
	
	
	/// This method is responsible for Deleting a Diagnostic Test from a Diagnostic Center
	@PutMapping("/delTestFromCenter/{cid}/{tid}")
	public ResponseEntity<DiagnosticTest> delTestFromCenter(@PathVariable("cid") int cid,@PathVariable("tid") int tid)
	{
		return new ResponseEntity<DiagnosticTest>(dts.removeTestFromDiagnosticCenter(cid, tid),HttpStatus.ACCEPTED);
	}
	
	
	/// This method is responsible for Deleting a Diagnostic Center through center id
	@DeleteMapping("/deleteDiagnosticCenter/{id}")
	public ResponseEntity<String> deleteDiagnosticCenter(@PathVariable("id") int id) 
	{
		return new ResponseEntity<String>(dcs.removeDiagnosticCenter(id),HttpStatus.ACCEPTED);
	}
	
	
	/// This method is responsible for changing the appointment status of an appointment by Admin
	@PutMapping("/changeAppointmentStatus/{appointId}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable("appointId") int appointId, @PathVariable("status") ApprovalStatus status)
	{
		Appointment a = asi.viewAppointment(appointId);
		a.setApprovalStatus(status);
		asi.updateAppointment(a);
		return new ResponseEntity<String>("Status is Changed",HttpStatus.ACCEPTED);
	}
	
	
	/// This controller method is responsible for adding a TestResult to its respective Appointment
	@PostMapping("/addTestResultToAppointment")
	public ResponseEntity<String> addTestToAppointment(@Valid @RequestBody addTestResultObjHolder obj)
	{
		Appointment a = asi.viewAppointment(obj.getAppointId());
		TestResult tr = new TestResult();
		tr.setId(obj.getId());
		tr.setTestReading(obj.getTestReading());
		tr.setCondition(obj.getCondition());
		tr.setAppointment(a);
		trs.addTestResult(tr);
		return new ResponseEntity<String>("Test result is added to the Appointment",HttpStatus.ACCEPTED);
	}
	
	
	/// This controller method is passing the client side a list of all appointments holding a particular Status
	@GetMapping("/getAppointmentList/{status}")
	public ResponseEntity<List> getAppointmentsByStatus(@PathVariable("status") ApprovalStatus status)
	{
		List<Appointment> appointmentList = ar.findAll();
		return new ResponseEntity<List>(appointmentList.stream().filter(a->a.getApprovalStatus().equals(status)).collect(Collectors.toList()),HttpStatus.FOUND);
	}
	
	/// This controller will give all the patient list
	@GetMapping("/getAllPatient")
	public ResponseEntity<List> getAllPatient()
	{
		return new ResponseEntity<List>(pr.findAll(),HttpStatus.OK);
	}
	
	
	/// This method will remove a patient through the patientId
	@DeleteMapping("/removePatient/{patientId}")
	public ResponseEntity<String> deletePatient(@PathVariable("patientId") int id)
	{
		psi.removePatient(id);
		return new ResponseEntity<String>("Patient is Deleted",HttpStatus.ACCEPTED);
	
	}
	
	
	/// This method will remove an appointment through id
	@DeleteMapping("/removeAppointment/{appointId}")
	public ResponseEntity<String> deleteAppointment(@PathVariable("appointId") int id)
	{
		asi.removeAppointment(asi.viewAppointment(id));
		return new ResponseEntity<String>("Appointment is Deleted",HttpStatus.ACCEPTED);
	}
	
	
	/// This controller method will remove a test_result
	@DeleteMapping("/removeTestResult/{testId}")
	public ResponseEntity<String> delete(@PathVariable("testId") int id)
	{
		trs.removeTestResult(id);
		return new ResponseEntity<String>("Test Result is Deleted",HttpStatus.ACCEPTED);
	}
	
	/// This controller method will show you a patient details
	@GetMapping("/show/{id}")
	public ResponseEntity<Patient> seePatient(@PathVariable("id") int id)
	{
		return new ResponseEntity<Patient>(psi.viewPatient(id),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/{patientName}/getAllTestresultByPatient")
	public ResponseEntity<List> getAllTestResultByPatient(@PathVariable("patientName") String name){
		return new ResponseEntity<List>(psi.getAllTestResult(name),HttpStatus.OK);
	}
	
}