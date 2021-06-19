package com.health.boot.entities;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Test_Result")
public class TestResult
{



	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double testReading;
	private String condition;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="appointment_no")
	private Appointment appointment;

	public TestResult() 
	{
		super();
	}

	public TestResult(int id, double testReading, String condition, Appointment appointment) 
	{
		super();
		this.id = id;
		this.testReading = testReading;
		this.condition = condition;
		this.appointment = appointment;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}


	public double getTestReading() 
	{
		return testReading;
	}

	public void setTestReading(double testReading) 
	{
		this.testReading = testReading;
	}

	public String getCondition() 
	{
		return condition;
	}

	public void setCondition(String condition) 
	{
		this.condition = condition;
	}

	public Appointment getAppointment() 
	{
		return appointment;
	}

	public void setAppointment(Appointment appointment) 
	{
		this.appointment = appointment;
	}


	@Override
	public String toString() 
	{
		return "TestResult [id=" + id + ", testReading=" + testReading + ", condition=" + condition + "]";
	}	
}
