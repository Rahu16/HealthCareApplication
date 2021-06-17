package com.health.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

	
	
}