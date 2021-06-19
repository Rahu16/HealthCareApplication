package com.health.boot.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Patient")
public class Patient
{
	
	@Id
	@Column(name="patientid")
	@NotNull(message = "Patient Id must not be empty")
	private int patientId;
	@NotEmpty(message = "Name must not be empty")
	private String name;
	@Size(min=10,max=13,message="Incorrect Phone Number Format")
	private String phoneNo;
	@Min(value=5,message="minimum age is 5")
	private int age;
	@NotEmpty(message = "Gender must not be empty")
	private String gender;
	
	@JsonIgnore
	@OneToMany(targetEntity = Appointment.class, mappedBy = "patient", fetch = FetchType.LAZY)
	private Set<Appointment> appointments=new HashSet<>();
	
	
	
	public Patient(int patientId, String name, String phoneNo, int age, String gender) 
	{
		super();
		this.patientId = patientId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.age = age;
		this.gender = gender;
	}

    public Patient() 
    {
		super();
	}


	public Patient(int patientId, String name, String phoneNo, int age, String gender, Set<Appointment> appointments) 
	{
		super();
		this.patientId = patientId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.age = age;
		this.gender = gender;
		this.appointments = appointments;
	}

    public int getPatientId() 
    {
		return patientId;
	}

	public void setPatientId(int patientId) 
	{
		this.patientId = patientId;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getPhoneNo() 
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) 
	{
		this.phoneNo = phoneNo;
	}

	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) 
	{
		this.age = age;
	}

	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public Set<Appointment> getAppointments() 
	{
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) 
	{
		this.appointments = appointments;
	}
	
		public void addAppointment(Appointment a) 
		{	
		a.setPatient(this);//this will avoid nested cascade
		this.getAppointments().add(a);
	}

	@Override
	public String toString() 
	{
		return "Patient [patientId=" + patientId + ", name=" + name + ", phoneNo=" + phoneNo + ", age=" + age
				+ ", gender=" + gender + ", appointments=" + appointments + "]";
	}
	}
