package com.health.boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.boot.entities.DiagnosticTest;
import com.health.boot.exceptions.DiagnosticTestNotFoundException;
import com.health.boot.services.IDiagnosticTestService;
import com.health.boot.services.IDiagnosticTestServiceImpls;

@RestController
@RequestMapping("dt")
public class DiagnosticTestController {
	@Autowired
	IDiagnosticTestService dts;
	
	//Fetching All Test
	@GetMapping("/getAllTests")
	public ResponseEntity<List> getAllTests(){
		return new ResponseEntity<List>(dts.getAllTest(),HttpStatus.ACCEPTED);
	}
	//Fetching Test by ID
	@GetMapping("testById/{id}")
	public ResponseEntity<DiagnosticTest> searchTestById(@PathVariable("id")int id) {
		return new ResponseEntity<DiagnosticTest>(dts.testById(id),HttpStatus.ACCEPTED);
	}
	//Fetching Test Center by ID
	@GetMapping("center/{id}")
	public ResponseEntity<List> getTestsOfDiagnosticCenter(@PathVariable("id")int centerId){
		return new ResponseEntity<List>(dts.getTestsOfDiagnosticCenter(centerId),HttpStatus.ACCEPTED);
	}
	//Fetching Test by Test Name
	@GetMapping("searchTestByName/{testName}")
	public ResponseEntity<DiagnosticTest> searchTestByName(@PathVariable ("testName") String testName){
		List<DiagnosticTest> list=dts.getAllTest();
		for(DiagnosticTest dt:list){
			String name=dt.getTestName();
			if(name.equals(testName)) {
				return new ResponseEntity<DiagnosticTest>(dt,HttpStatus.ACCEPTED);
			}
		}
		throw new DiagnosticTestNotFoundException("Test not found with name: "+testName);
	}
	

}
