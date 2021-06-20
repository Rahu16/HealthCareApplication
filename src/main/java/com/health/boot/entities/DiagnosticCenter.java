package com.health.boot.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="diagnostic_center")
public class DiagnosticCenter 
{
	
	@Id
	@Column(name="dcenter_id")
	private int id;
	@Column(name="centername")
	@NotEmpty(message = "CenterName must not be empty")
	private String name;
	@Column(name="contactno")
	@Size(min=10,max=13,message="Incorrect Phone Number Format")
	private String contactNo;
	@Column(name="address")
	@NotEmpty(message = "Address must not be empty")
	private String address;
	@Column(name="email")
	@NotEmpty(message = "Email must not be empty")
	@Email(message = "Email must be a valid email address")
	private String contactEmail;
    @ManyToMany()
	@JoinTable(name="dcenter_dtest",                            // join table name will be dcenter_dtest
		joinColumns = {@JoinColumn(name = "dcenter_id")}, 
		inverseJoinColumns = {@JoinColumn(name = "dtest_id")})

	private Set<DiagnosticTest> tests = new HashSet<>();
	
    //Constructor without parameters
	public DiagnosticCenter() 
	{
		super();
	}
	
	//constructor with parameters
	public DiagnosticCenter(int id, String name, String contactNo, String address, String contactEmail) 
	{
		super();
		this.id = id;
		this.name = name;
		this.contactNo = contactNo;
		this.address = address;
		this.contactEmail = contactEmail;
	}

	public DiagnosticCenter(int id, String name, String contactNo, String address, String contactEmail, Set<DiagnosticTest> test) 
	{
		super();
		this.id = id;
		this.name = name;
		this.contactNo = contactNo;
		this.address = address;
		this.contactEmail = contactEmail;
		this.tests = tests;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getContactNo() 
	{
		return contactNo;
	}

	public void setContactNo(String contactNo) 
	{
		this.contactNo = contactNo;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getContactEmail() 
	{
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) 
	{
		this.contactEmail = contactEmail;
	}


 	public Set<DiagnosticTest> getTests() 
 	{
 		return tests;
 	}
 
 	public void setTests(Set<DiagnosticTest> tests1) 
 	{
 		this.tests = tests1;
 	}

 	public void addTestToCenter(DiagnosticTest t) 
 	{
 		this.getTests().add(t);
 	}
	
	public void addtest(DiagnosticTest t) 
	{
		this.getTests().add(t);
	}
	

	
}
