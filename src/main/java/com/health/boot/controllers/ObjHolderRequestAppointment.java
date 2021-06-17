package com.health.boot.controllers;

import java.time.LocalDate;

public class ObjHolderRequestAppointment {
	
	private int appointId;
	private String patientName;
	private int diagCenterId;
	private String testName;
	private LocalDate date;
	
	public int getAppointId() {
		return appointId;
	}
	public void setAppointId(int appointId) {
		this.appointId = appointId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getDiagCenterId() {
		return diagCenterId;
	}
	public void setDiagCenterId(int diagCenterId) {
		this.diagCenterId = diagCenterId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
