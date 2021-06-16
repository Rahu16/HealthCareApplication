package com.health.boot.services;

import java.util.List;
import java.util.Set;

import com.health.boot.entities.Appointment;
import com.health.boot.exceptions.AppointmentNotFoundException;

public interface IAppointmentService {

	Appointment addAppointment(Appointment appointment)	throws RuntimeException;
	Appointment removeAppointment(Appointment appointment)	throws RuntimeException;
	
	Set<Appointment> viewAppointments(String patientName) throws AppointmentNotFoundException;
	Appointment viewAppointment(int appointmentId) throws AppointmentNotFoundException;
	Appointment updateAppointment(Appointment appointment) throws AppointmentNotFoundException;
	List<Appointment> getApppointmentList(int centreId, String test, int status) throws RuntimeException;
	
}
