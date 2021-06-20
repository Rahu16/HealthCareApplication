package com.health.boot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.ApprovalStatus;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.entities.Patient;
import com.health.boot.exceptions.AppointmentExistException;
import com.health.boot.exceptions.AppointmentNotFoundException;
import com.health.boot.exceptions.PatientNotFoundException;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.ITestResultRepository;
import com.health.boot.repository.PatientRepository;

///// All Service methods are Implemented here for Appointment
@Service
public class IAppointmentServiceImpl implements IAppointmentService
{

	@Autowired
	AppointmentRepository ar;
	
	@Autowired
	PatientRepository pr;
	
	@Autowired
	ITestResultRepository tr;
	
	@Autowired
	IDiagnosticCenterService dcs;
	
	
	
	/// Adding an Appointment to a particular Patient
	@Override
	public Appointment addAppointment(Appointment appointment) throws RuntimeException 
	{
		Optional<Appointment> appoint = ar.findById(appointment.getId());
		if(appoint.isPresent())
			throw new AppointmentExistException("Appointment with Same Id is found");
		Patient p = pr.findById(appointment.getPatient().getPatientId()).get();
		pr.save(p);
		return ar.save(appointment);
	}

	
	/// Removing an Appointment from a Particular Patient and returning the Appointment
	@Override
	public Appointment removeAppointment(Appointment appointment) throws RuntimeException 
	{
		Optional<Appointment> app = ar.findById(appointment.getId());
		if(!app.isPresent())
			throw new AppointmentNotFoundException("Appoint is not found to delete");
		Appointment appoint = app.get();
		Patient p = appoint.getPatient();
		p.getAppointments().remove(appoint);
		pr.save(p);
		ar.delete(appoint);
		return appoint;
	}

	
	/// Getting a Set of Appointments for a Particular Patient by giving Patient's name as an Arguement
	@Override
	public Set<Appointment> viewAppointments(String patientName) throws AppointmentNotFoundException 
	{
		Patient p = pr.findPatientByName(patientName);
		if(p==null)
			throw new PatientNotFoundException("Patient is Not Found with this Name");
		return p.getAppointments();
	}

	
	/// Getting an Appointment Object just by AppointmentId
	@Override
	public Appointment viewAppointment(int appointmentId) throws AppointmentNotFoundException 
	{
		Optional<Appointment> a = ar.findById(appointmentId);
		if(!a.isPresent())
			throw new AppointmentNotFoundException("Appointment is Not Found to View");
		return a.get();
	}

	
	/// Updating an Appointment Details by passing the Appointment Object
	@Override
	public Appointment updateAppointment(Appointment appointment) throws AppointmentNotFoundException 
	{
		Optional<Appointment> appoint = ar.findById(appointment.getId());
		if(appoint.isEmpty())
			throw new AppointmentNotFoundException("Appointment Not Found to Update");
		Patient p = pr.findById(appoint.get().getPatient().getPatientId()).get();
		Appointment a = ar.save(appointment);
		pr.save(p);
		return a;
}

	
	/// Getting a list of Appointments for a Particular Diagnostic Test in a particular Diagnostic Center
	@Override
	public List<Appointment> getApppointmentList(int centreId, String test, ApprovalStatus status) throws RuntimeException 
	{
		List<Appointment> filteredAppointments = new ArrayList<>();
		List<Appointment> list = ar.findAll();
		List<Appointment> list1 = list.stream().filter(a->(a.getDiagnosticCenter().getId()==centreId && a.getApprovalStatus().equals(status)))
				.collect(Collectors.toList());
		for(Appointment a: list1) 
		{
			Set<DiagnosticTest> set = a.getDiagnosticTests();
			for(DiagnosticTest t: set) 
			{
				if(t.getTestName()==test)
					filteredAppointments.add(a);					
			}
		}
		return filteredAppointments;
		}
	}
