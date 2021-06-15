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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="diagnostic_center")
public class DiagnosticCenter {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dcenter_id")
	private int id;
	@Column(name="centername")
	private String name;
	@Column(name="contactno")
	private String contactNo;
	@Column(name="address")
	private String address;
	@Column(name="email")
	private String contactEmail;
    @ManyToMany()
	@JoinTable(name="dcenter_dtest",
		joinColumns = {@JoinColumn(name = "dcenter_id")}, 
		inverseJoinColumns = {@JoinColumn(name = "dtest_id")})

	private Set<DiagnosticTest> tests = new HashSet<>();
	
	public DiagnosticCenter() {
		super();
		
	}
	
	public DiagnosticCenter(int id, String name, String contactNo, String address, String contactEmail) {
		super();
		this.id = id;
		this.name = name;
		this.contactNo = contactNo;
		this.address = address;
		this.contactEmail = contactEmail;
	}



	public DiagnosticCenter(int id, String name, String contactNo, String address, String contactEmail, Set<DiagnosticTest> test) {
		super();
		this.id = id;
		this.name = name;
		this.contactNo = contactNo;
		this.address = address;
		this.contactEmail = contactEmail;
		this.tests = tests;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}


 	public Set<DiagnosticTest> getTests() {
 		return tests;
 	}
 
 	public void setTests(Set<DiagnosticTest> tests1) {
 		this.tests = tests1;
 	}

 	public void addTestToCenter(DiagnosticTest t) {
 		this.getTests().add(t);
 	}
	
	public void addtest(DiagnosticTest t) {
		this.getTests().add(t);
	}
	

	
}
