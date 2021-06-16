package com.health.boot.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.Patient;
import com.health.boot.exceptions.AppointmentExistException;
import com.health.boot.exceptions.AppointmentNotFoundException;
import com.health.boot.exceptions.PatientNotFoundException;
import com.health.boot.repository.AppointmentRepository;
import com.health.boot.repository.PatientRepository;

@Service
public class IAppointmentServiceImpl implements IAppointmentService{

	@Autowired
	AppointmentRepository ar;
	
	@Autowired
	PatientRepository pr;
	
	
	@Override
	public Appointment addAppointment(Appointment appointment) throws RuntimeException {
		Optional<Appointment> appoint = ar.findById(appointment.getId());
		if(appoint.isPresent())
			throw new AppointmentExistException("Appointment with Same Id is found");
		Patient p = pr.findById(appoint.get().getPatient().getPatientId()).get();
		pr.save(p);
		return ar.save(appointment);
	}

	@Override
	public Appointment removeAppointment(Appointment appointment) throws RuntimeException {
		Optional<Appointment> app = ar.findById(appointment.getId());
		if(!app.isPresent())
			throw new AppointmentNotFoundException("Appoint is not found to delete");
		Appointment appoint = app.get();
		Patient p = appoint.getPatient();
		p.getAppointments().remove(appoint);
		Patient p1 = pr.save(p);
		ar.delete(appoint);
		return appoint;
	}

	@Override
	public Set<Appointment> viewAppointments(String patientName) throws AppointmentNotFoundException {
		Patient p = pr.findPatientByName(patientName);
		if(p==null)
			throw new PatientNotFoundException("Patient is Not Found with this Name");
		return p.getAppointments();
	}

	@Override
	public Appointment viewAppointment(int appointmentId) throws AppointmentNotFoundException {
		Optional<Appointment> a = ar.findById(appointmentId);
		if(!a.isPresent())
			throw new AppointmentNotFoundException("Appointment is Not Found to View");
		return a.get();
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) throws AppointmentNotFoundException {
		Optional<Appointment> appoint = ar.findById(appointment.getId());
		if(appoint.isEmpty())
			throw new AppointmentNotFoundException("Appointment Not Found to Update");
		Patient p = pr.findById(appoint.get().getPatient().getPatientId()).get();
		Appointment a = ar.save(appointment);
		pr.save(p);
		return a;

	}

	@Override
	public List<Appointment> getApppointmentList(int centreId, String test, int status) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
