package com.health.boot.entities;



import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Appointment 
{
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private LocalDate appointmentDate;
	private ApprovalStatus approvalStatus;
	
	@OneToMany(targetEntity = DiagnosticTest.class, fetch = FetchType.LAZY)
	private Set<DiagnosticTest> diagnosticTests;
	
	@ManyToOne(targetEntity = Patient.class, fetch = FetchType.LAZY)
	@JoinColumn(name="patientid",referencedColumnName = "patientId")
	private Patient patient;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="diago_center")
	private DiagnosticCenter diagnosticCenter;
	
	@OneToMany(targetEntity = TestResult.class, mappedBy = "appointment", fetch = FetchType.LAZY)
	private Set<TestResult> testResult;
	
	
	public Appointment(int id, LocalDate appointmentDate, ApprovalStatus approvalStatus, Patient patient,
			Set<TestResult> testResult) 
	{
		super();
		this.id = id;
		this.appointmentDate = appointmentDate;
		this.approvalStatus = approvalStatus;
		this.patient = patient;
		this.testResult = testResult;
	}
	
    public Appointment() 
    {
		super();
	}

    public Set<TestResult> getTestResult() 
    {
		return testResult;
	}

    public void setTestResult(Set<TestResult> testResult) 
    {
		this.testResult = testResult;
	}

     public int getId() 
     {
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public LocalDate getAppointmentDate() 
	{
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) 
	{
		this.appointmentDate = appointmentDate;
	}

	public ApprovalStatus getApprovalStatus() 
	{
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) 
	{
		this.approvalStatus = approvalStatus;
	}

    public Patient getPatient() 
    {
		return patient;
	}

	public void setPatient(Patient patient) 
	{
		this.patient = patient;
	}

	
	@Override
	public String toString() 
	{
		return "Appointment [id=" + id + ", appointmentDate=" + appointmentDate + ", approvalStatus=" + approvalStatus
				+ ", patient=" + patient + "]";
	}

    public Set<DiagnosticTest> getDiagnosticTests() 
    {
		return diagnosticTests;
	}

    public void setDiagnosticTests(Set<DiagnosticTest> diagnosticTests) 
    {
		this.diagnosticTests = diagnosticTests;
	}

    public DiagnosticCenter getDiagnosticCenter() 
    {
		return diagnosticCenter;
	}

    public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) 
    {
		this.diagnosticCenter = diagnosticCenter;
	}

    public void addDiagnosticTest(DiagnosticTest dTest) 
    {			//this will avoid nested cascade
		this.getDiagnosticTests().add(dTest);
	}
	}
