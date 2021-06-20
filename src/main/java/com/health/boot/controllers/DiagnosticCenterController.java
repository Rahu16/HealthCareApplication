package com.health.boot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.repository.DiagnosticCenterRepository;
import com.health.boot.services.IDiagnosticCenterService;
import com.health.boot.services.IDiagnosticCenterServiceImpls;
import com.health.boot.services.IDiagnosticTestService;


@RestController
@RequestMapping("/dc")
public class DiagnosticCenterController 
{

	@Autowired
	IDiagnosticCenterService dcs;
	
	@Autowired
	IDiagnosticTestService dts;
	
	/// This controller method returns all the diagnostic centers and by calling getAllDiagnosticCenters method in IDiagnosticCenterService.
	@GetMapping()
	public List<DiagnosticCenter> getDiagnosticCenters()
	{
		return dcs.getAllDiagnosticCenters();
	}
	
	/// This controller method is used to search the diagnostic center by giving argument as center id. 
	@GetMapping("{id}")
	public ResponseEntity<DiagnosticCenter> searchCenter(@PathVariable("id") int id) 
	{
		return new ResponseEntity<DiagnosticCenter>(dcs.getDiagnosticCenterById(id),HttpStatus.ACCEPTED);
	}
	
	/// This controller method is used to search the diagnostic center by giving argument as center name. 
	@GetMapping("searchCenterByName/{centername}")
	public ResponseEntity<DiagnosticCenter> searchCenterByName(@PathVariable("centername") String name) 
	{
		return new ResponseEntity<DiagnosticCenter>(dcs.getDiagnosticCenterByName(name),HttpStatus.ACCEPTED);
	}
	
	/// It returns the test details of particular diagnostic center.
	@GetMapping("{cid}/{testname}")
	public ResponseEntity<DiagnosticTest> viewDetails(@PathVariable("cid") int centerId,@PathVariable("testname") String testname) 
	{
		return new ResponseEntity<DiagnosticTest>(dcs.viewTestDetails(centerId, testname),HttpStatus.ACCEPTED);
	}
	
	/// It returns all the centers which are having the particular test which is given as argument. 
	@GetMapping("sortCentersByTestName/{testName}")
	public ResponseEntity<List> sortCentersByTestName(@PathVariable("testName") String testName)
	{
		List<DiagnosticCenter> list=new ArrayList<>();
		List<DiagnosticCenter> list1=dcs.getAllDiagnosticCenters();
		for(DiagnosticCenter dc:list1) 
		{
			Set<DiagnosticTest>set=dc.getTests(); 
			for(DiagnosticTest dt:set) 
			{
				if(dt.getTestName().equals(testName)) 
					list.add(dc);
			}
		}
		return new ResponseEntity<List>(list,HttpStatus.ACCEPTED);
	}
	
	/// It sorts the centers based on the address given. 
	@GetMapping("sortCentersByAddress/{address}")
	public ResponseEntity<List> sortCentersByAddress(@PathVariable("address") String address)
	{
		List<DiagnosticCenter> list=new ArrayList<>();
		List<DiagnosticCenter> list1=dcs.getAllDiagnosticCenters();
		for(DiagnosticCenter dc:list1) 
		{
			if(dc.getAddress().equals(address)) 
			{
				list.add(dc);
			}
		}	
		return new ResponseEntity<List>(list,HttpStatus.ACCEPTED);
	}
	}
