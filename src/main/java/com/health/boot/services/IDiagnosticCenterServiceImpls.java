package com.health.boot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.exceptions.DiagnosticCenterAlReadyExistsException;
import com.health.boot.exceptions.DiagnosticCenterNotFoundException;
import com.health.boot.exceptions.DiagnosticTestNotFoundException;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.DiagnosticCenterRepository;
import com.health.boot.repository.DiagnosticTestRepository;

@Service
public class IDiagnosticCenterServiceImpls implements IDiagnosticCenterService 
{
	
	@Autowired
	DiagnosticCenterRepository dcr;
	
	@Autowired
	DiagnosticTestRepository dtr;
	
	@Autowired
	AppointmentRepository ar;
	
	
	/// This method is used to get all Diagnostic Centers.
	@Override
	public List<DiagnosticCenter> getAllDiagnosticCenters()
	{
		return dcr.findAll();
	}
	
	
	/* This method is used to create a new Diagnostic Center.
	 *  If the given given id is already presents then it throws DiagnosticCenterAlReadyExistsException.
	 */
	@Override
	public DiagnosticCenter createDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws DiagnosticCenterAlReadyExistsException
	{
		int id=diagnosticCenter.getId();
		Optional<DiagnosticCenter> dc=dcr.findById(id);
		if(dc.isEmpty()) 
		{
			dcr.save(diagnosticCenter);
			return diagnosticCenter;
		}
		else 
		{
			throw new DiagnosticCenterAlReadyExistsException("Diagnostic center with id "+id+" All ready existed");
		}
	}
	
	
	/* This method is used to search the Diagnostic Center by giving centerId as argument.
	 * It throws DiagnosticCenterNotFoundException, if there is no diagnostic center with the given id.
	 */
	@Override
	public DiagnosticCenter getDiagnosticCenterById(int diagnosticCenterId) 
	{
		Optional<DiagnosticCenter> dc=dcr.findById(diagnosticCenterId);
		if(dc.isEmpty()) 
		{
			throw new DiagnosticCenterNotFoundException("There is no Diagnostic Center with id: "+diagnosticCenterId);
		}
		return dc.get();
	}
	
	
	/* This method is used to search the Diagnostic Center by giving center name as argument.
	 * It throws DiagnosticCenterNotFoundException, if there is no diagnostic center with the given name.
	 */
	@Override
	public DiagnosticCenter getDiagnosticCenterByName(String centername) 
	{
		ArrayList<DiagnosticCenter> list=(ArrayList<DiagnosticCenter>) dcr.findAll();
		for(DiagnosticCenter dc:list) 
		{
			if(dc.getName().equals(centername)) 
			return dc;
		}
		throw new DiagnosticCenterNotFoundException("Dignostic center is not available with name"+centername);
	}
	
	
	/* This method is used to delete the diagnostic center  by giving center id as argument. 
	 * It throws DiagnosticCenterNotFoundException, if there is no diagnostic center with the given id.
	 */
	@Override
	public String removeDiagnosticCenter(int id) throws DiagnosticCenterNotFoundException
	{
		if(dcr.findById(id).isPresent()) 
		{
			DiagnosticCenter dc=dcr.findById(id).get();
			dcr.delete(dc);
			return "Deleted";
			}
		else
			throw new DiagnosticCenterNotFoundException("Dignostic center is not found with "+id);
	}
	
	
	/*  This method is used to add a test inside the center by giving test id and center id as argument.
	 *  It throws DiagnosticTestNotFoundException, if test id is not found in data base.
	 *  It throws DiagnosticCenterNotFoundException, if center id is not found in data base.
	 */ 
	@Override
	public String addTestInCenter(int testId,int centerId) 
	{
		Optional<DiagnosticTest> dt=dtr.findById(testId);
		Optional<DiagnosticCenter> dc=dcr.findById(centerId);
		if(dc.isEmpty())
			throw new DiagnosticCenterNotFoundException("DiagnosticCenter Not Found With Id: "+centerId);
		if(dt.isEmpty())
			throw new DiagnosticTestNotFoundException("DiagnosticTest Not Found with Id: "+testId);
		
		dc.get().addtest(dt.get());
		dcr.save(dc.get());
		return "Added";
	}
	
	
	/* This method is used to update the diagnostic center details.
	 * It throws DiagnosticCenterNotFoundException, if the center id in the object argument is not found.
	 */
	@Override
	public String updateDiagnosticCenter(DiagnosticCenter diagnosticCenter) 
	{
		int id=diagnosticCenter.getId();
		Optional<DiagnosticCenter> dc=dcr.findById(id);
		if(dc.isEmpty()) 
		{
			throw new DiagnosticCenterNotFoundException("Dignostic center is not available with provided details and not possible");
			}
		dcr.save(diagnosticCenter);
		return "Updated";
	}
	
	
	/*  It returns the test details in a particular center.
	 *  It throws the DiagnosticCenterNotFoundException, if the center id is not found.
	 *  It throws the DiagnosticTestNotFoundException, if the test name is not present inside the center.
	 */
	@Override
	public DiagnosticTest viewTestDetails(int centerId, String testName) 
	{
		Optional<DiagnosticCenter> dc=dcr.findById(centerId);
		if(dc.isEmpty()) 
		{
			throw new DiagnosticCenterNotFoundException("Center not found with Id: "+centerId);
		}
		DiagnosticCenter dc1=dcr.findById(centerId).get();
		Set<DiagnosticTest> set=dc1.getTests();
		for(DiagnosticTest t:set) 
		{
			if(t.getTestName().equals(testName)) 
			{
				return t;
				}
		}
		throw new DiagnosticTestNotFoundException("Test not found with name as "+testName+" in the center.");
	}
	
	
	/*  It returns the list of all appointments in a particular center.
	 * 
	 */
	@Override
	public List<Appointment> getListOfAppointments(String centerName)
	{
		List<Appointment> list=new ArrayList<>();
		List<Appointment> list2=ar.findAll();
		for(Appointment a:list2) 
		{
			String name=a.getDiagnosticCenter().getName();
			if(name.equals(centerName)) 
			{
				list.add(a);
			}
		}	
		return list;
	}
	
}

