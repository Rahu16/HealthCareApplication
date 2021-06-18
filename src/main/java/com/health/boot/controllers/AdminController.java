package com.health.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.services.IDiagnosticCenterService;
import com.health.boot.services.IDiagnosticTestService;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	IDiagnosticCenterService dcs;
	
	@Autowired
	IDiagnosticTestService dts;
	
	@PostMapping("/newDiagnosticCenter")
	public ResponseEntity<DiagnosticCenter> createNewDiagnosticCenter(@RequestBody DiagnosticCenter dc) {
		
		return new ResponseEntity<DiagnosticCenter>(dcs.createDiagnosticCenter(dc),HttpStatus.CREATED);
	}
	
	@PostMapping("/createNewTest")
	public ResponseEntity<DiagnosticTest> createNewTest(@RequestBody DiagnosticTest t) {
		return new ResponseEntity<DiagnosticTest>(dts.CreateNewTest(t),HttpStatus.CREATED);
	}
		
	@PutMapping("/updateTestDetails")
	public ResponseEntity<DiagnosticTest> updateTestDetails(@RequestBody DiagnosticTest test) {
		return new ResponseEntity<DiagnosticTest>(dts.updateTestDetail(test),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateCenterDetails")
	public ResponseEntity<String> updateCenterDetails(@RequestBody DiagnosticCenter dc) {
		return new ResponseEntity<String>(dcs.updateDiagnosticCenter(dc),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/addTestInCenter/{tid}/{cid}")
	public ResponseEntity<String> addTestInCenter(@PathVariable("cid") int cid,@PathVariable("tid") int tid) {
		return new ResponseEntity<String>(dcs.addTestInCenter(tid, cid),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/delTestFromCenter/{cid}/{tid}")
	public ResponseEntity<DiagnosticTest> delTestFromCenter(@PathVariable("cid") int cid,@PathVariable("tid") int tid) {
		return new ResponseEntity<DiagnosticTest>(dts.removeTestFromDiagnosticCenter(cid, tid),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteDiagnosticCenter/{id}")
	public ResponseEntity<String> deleteDiagnosticCenter(@PathVariable("id") int id) {
		return new ResponseEntity<String>(dcs.removeDiagnosticCenter(id),HttpStatus.ACCEPTED);
	}

	
	
}