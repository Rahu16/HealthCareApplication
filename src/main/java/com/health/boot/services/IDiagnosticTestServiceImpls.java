package com.health.boot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.exceptions.AllreadyDiagnosticTestExistException;
import com.health.boot.exceptions.DiagnosticCenterNotFoundException;
import com.health.boot.exceptions.DiagnosticTestNotFoundException;
import com.health.boot.repository.DiagnosticCenterRepository;
import com.health.boot.repository.DiagnosticTestRepository;
@Service
public class IDiagnosticTestServiceImpls implements IDiagnosticTestService
{
	
	
	@Autowired
	DiagnosticTestRepository dtr;
	
	@Autowired
	DiagnosticCenterRepository dcr;
	
	@Override
	public List<DiagnosticTest> getAllTest(){
		return dtr.findAll();
	}
	
	@Override
	public DiagnosticTest CreateNewTest(DiagnosticTest test) {
		int testid=test.getId();
		Optional<DiagnosticTest> t=dtr.findById(testid);
		if(t.isEmpty()) {
			dtr.save(test);
			return test;
		}
		else 
		throw new AllreadyDiagnosticTestExistException("Test with id "+testid+" exists already");
	}
	
	@Override
	public DiagnosticTest updateTestDetail(DiagnosticTest test) 
	{
		int testid=test.getId();
		Optional<DiagnosticTest> t=dtr.findById(testid);
		if(t.isEmpty()) 
		{
			throw new DiagnosticTestNotFoundException("there is no test with id "+testid);
			}
		else 
		{
			 dtr.save(test);
			 return test;
			 }
	}
	
	@Override
	public DiagnosticTest removeTestFromDiagnosticCenter(int centerId, int testId) 
	{
		if(dcr.existsById(centerId)) 
		{
		    if(dtr.existsById(testId)) 
		    {
		    	DiagnosticCenter dc=dcr.findById(centerId).get();		  
		    	DiagnosticTest t=dtr.findById(testId).get();
		    	Set<DiagnosticTest> list= dc.getTests();
		    	boolean x= list.remove(t);
		    	if(x) 
		    	{ 
		    		dcr.save(dc);
		    		return t;
		    		}
		    	else throw new DiagnosticTestNotFoundException(testId+" is not found in the center "+centerId);
		    	}
		     else throw new DiagnosticTestNotFoundException("Test is not found with id"+testId);
		    }
		throw new DiagnosticCenterNotFoundException("Center Not Found with id "+centerId);
		
	}
	
	@Override
	public List<DiagnosticTest> getTestsOfDiagnosticCenter(int centerId) 
	{
		DiagnosticCenter dc=dcr.findById(centerId).get();
		if(dc==null) 
		{
			throw new DiagnosticCenterNotFoundException("Center not found with id "+centerId);
		}
		else {
		DiagnosticCenter dc2=dcr.findById(centerId).get();
		Set<DiagnosticTest> set = dc2.getTests();
		List<DiagnosticTest> list=new ArrayList<>();
		for(DiagnosticTest d:set) 
		{
			list.add(d);
			}
		return list;
		}
		}
	
	public DiagnosticTest testById(int id) {
		Optional<DiagnosticTest> test =dtr.findById(id);
		if(test.isEmpty()) 
		{
			throw new DiagnosticTestNotFoundException("There is no Test with id: "+id);
		}
		else 
		{
			return test.get();
		}
	}
	
}