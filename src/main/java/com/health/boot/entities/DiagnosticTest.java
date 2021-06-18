package com.health.boot.entities;


import java.util.HashSet;
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
@Table(name="diagnostic_test")
public class DiagnosticTest 
{

		@Id
		//@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="dtest_id")
		private int id;
		@Column(name="testname")
		private String testName;
		@Column(name="testprice")
		private double testPrice;
		@Column(name="normalvalue")
		private String normalValue;
		@Column(name="units")
		private String units;
		@JsonIgnore
 		@ManyToMany(mappedBy="tests",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 		private Set<DiagnosticCenter> diagnosticCenters = new HashSet<>();
		
 		public DiagnosticTest(int id, String testName, double testPrice, String normalValue, String units) 
 		{
			super();
			this.id = id;
			this.testName = testName;
			this.testPrice = testPrice;
			this.normalValue = normalValue;
			this.units = units;
		}

		public DiagnosticTest(int id, String testName, double testPrice, String normalValue, String units,
				Set<DiagnosticCenter> diagnosticCenters) 
		{
			super();
			this.id = id;
			this.testName = testName;
			this.testPrice = testPrice;
			this.normalValue = normalValue;
			this.units = units;
			this.diagnosticCenters = diagnosticCenters;
		}

		public DiagnosticTest() 
		{
			super();
		}

		public int getId() 
		{
			return id;
		}

		public void setId(int id) 
		{
			this.id = id;
		}

		public String getTestName() 
		{
			return testName;
		}

		public void setTestName(String testName) 
		{
			this.testName = testName;
		}

		public double getTestPrice() 
		{
			return testPrice;
		}

		public void setTestPrice(double testPrice) 
		{
			this.testPrice = testPrice;
		}

		public String getNormalValue() 
		{
			return normalValue;
		}

		public void setNormalValue(String normalValue) 
		{
			this.normalValue = normalValue;
		}

		public String getUnits() {
			return units;
		}

		public void setUnits(String units) 
		{
			this.units = units;
		}
//
 		public Set<DiagnosticCenter> getDiagnosticCenters() 
 		{
 			return diagnosticCenters;
 		}
 
 		public void setDiagnosticCenters(Set<DiagnosticCenter> diagnosticCenters) 
 		{
 			this.diagnosticCenters = diagnosticCenters;
				}
 		

		@Override
		public String toString() 
		{
			return "DiagnosticTest [id=" + id + ", testName=" + testName + ", testPrice=" + testPrice + ", normalValue="
					+ normalValue + ", units=" + units + ", diagnosticCenters=" + diagnosticCenters + "]";
		}

 		}