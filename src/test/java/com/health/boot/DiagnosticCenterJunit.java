package com.health.boot;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.health.boot.entities.Appointment;
import com.health.boot.entities.DiagnosticCenter;
import com.health.boot.entities.DiagnosticTest;
import com.health.boot.exceptions.DiagnosticCenterAlReadyExistsException;
import com.health.boot.exceptions.DiagnosticCenterNotFoundException;
import com.health.boot.exceptions.DiagnosticTestNotFoundException;
import com.health.boot.repository.DiagnosticCenterRepository;
import com.health.boot.services.IDiagnosticCenterService;

@SpringBootTest
class DiagnosticCenterJunit {
	
	@Autowired
	IDiagnosticCenterService dcs;
	
	@Autowired
	DiagnosticCenterRepository dcr;
	
	// test for all get all DiagnosticCenters
	@Test
	void testGetAllCenter() {
		int l1=dcs.getAllDiagnosticCenters().size();
		int l2=dcr.findAll().size();
		assertEquals(l1, l2);
	}
	
	// test for  createDiagnosticCenter 
	@Test
	void testCreateDiagnostiCenter() {
		DiagnosticCenter dc=new DiagnosticCenter();
		dc.setAddress("Hyderabad");
		dc.setContactEmail("gandhidcenter@ymail.com");
		dc.setContactNo("7559999887");
		dc.setName("IndDcenter");
		dc.setId(1255);
		DiagnosticCenter dc1=dcs.createDiagnosticCenter(dc);
		assertEquals(dc1.toString(),dc.toString());
	}
	
	// Test For DiagnosticCenterAlReadyExistsException in CreateDiagnosticCenter Method
	@Test
	void exceptionTestCreateDiagnosticCenter() {
		DiagnosticCenter dc=new DiagnosticCenter();
		dc.setAddress("Kolkata");
		dc.setContactEmail("rajivdcenter@ymail.com");
		dc.setContactNo("7559999887");
		dc.setName("RajivDcenter");
		dc.setId(1441);
		assertThrows(DiagnosticCenterAlReadyExistsException.class,()->dcs.createDiagnosticCenter(dc));
	}
	
	//Test For getDiagnosticCenter Method
	@Test
	void testGetDiagnosticCenterById() {
		int centerId =1221;
		DiagnosticCenter dc=dcs.getDiagnosticCenterById(centerId);
		assertTrue(!(dc==null));
	}
	
	// Test For DiagnosticCenterNotFoundException in getCenterById Method 
	@Test
	void exceptionTestGetDiagnosticCenterById() {
		int centerId=1701;
		assertThrows(DiagnosticCenterNotFoundException.class,()->dcs.getDiagnosticCenterById(centerId));
	}
	
	// Test For getDiagnosticCenterByName Method
	@Test
	void testgetDiagnosticCenterByName() {
		String name="apolodcenter";
		DiagnosticCenter dc=dcs.getDiagnosticCenterByName(name);
		assertTrue(dc!=null);
	}
	
	// Test For DiagnosticCenterNotFoundException in getCenterByName Method
	@Test
	void exceptionTestGetDiagnosticCenterByName() {
		String name="NotCenter";
		assertThrows(DiagnosticCenterNotFoundException.class,()->dcs.getDiagnosticCenterByName(name));
	}
	
	// Test For removeDiagnosticCenter Method
	@Test
	void testRemoveDiagnosticCente() {
		int centerId=1200;
		String rem=dcs.removeDiagnosticCenter(centerId);
		assertEquals("Deleted",rem);
	}
	
	// Test For DiagnosticCenterNotFoundException in removeDiagnosticCenter Method
	@Test
	void exceptionTestRemoveDiagnosticCente() {
		int centerId=001;
		assertThrows(DiagnosticCenterNotFoundException.class,()->dcs.removeDiagnosticCenter(centerId));
	}
	
	// Test for addTestInCenter Method
	@Test
	void testAddTestInCenter() {
		int centerId=1221;
		int testId=143;
		String st=dcs.addTestInCenter(testId, centerId);
		assertTrue(st.equals("Added"));
	}
	
	// Test For DiagnosticCenterNotFoundException In addTestInCenter Method
	@Test
	void exceptionTestAddTestInCenter() {
		int centerId=1231;
		int testId=108;
		assertThrows(DiagnosticCenterNotFoundException.class,()->dcs.addTestInCenter(testId, centerId));
	}
	
	// Test For DiagnosticTestNotFoundException In addTestInCenter Method
	@Test
	void exceptionTestAddTestInCenterr() {
		int centerId=1221;
		int testId=178;
		assertThrows(DiagnosticTestNotFoundException.class,()->dcs.addTestInCenter(testId, centerId));
	}
	
	// Test For updateDiagnosticCenter Method
	@Test
	void testUpdateDiagnosticCenter() {
		DiagnosticCenter dc= new DiagnosticCenter();
		dc.setId(1500);
		dc.setName("apolodcenter");
		dc.setAddress("Mumbai");
		dc.setContactEmail("Bandradcenter@gmail.com");
		dc.setContactNo("6666777766");
		String upd=dcs.updateDiagnosticCenter(dc);
		assertTrue(upd.equals("Updated"));
		
	}
	
	// Test For DiagnosticCenterNotFoundException in updateDiagnosticCenter Method
	@Test
	void exceptionTestUpdateDiagnosticCenter() {
		DiagnosticCenter dc= new DiagnosticCenter();
		dc.setId(1765);
		dc.setName("BandraDcenter");
		dc.setAddress("Mumbai");
		dc.setContactEmail("Bandradcenter@gmail.com");
		dc.setContactNo("6666666666");
		assertThrows(DiagnosticCenterNotFoundException.class,()->dcs.updateDiagnosticCenter(dc));
	}
	
	// Test For viewTestDetails Method
	@Test
	void testViewTestDetails() {
		int centerId=1410;
		String testName="Lungs Profile";
		DiagnosticTest dt=dcs.viewTestDetails(centerId, testName);
		assertEquals(dt.getTestName(),testName);
	}
	
	// Test For DiagnosticCenterNotFound in viewTestDetails Method
	@Test
	void exceptionTestviewTestDetails() {
		int centerId=1006;
		String testName="Lungs Profile";
		assertThrows(DiagnosticCenterNotFoundException.class,()->dcs.viewTestDetails(centerId, testName));
	}
	
	// Test For DiagnosticTestNotFound in viewTestDetails Method
		@Test
		void exceptionTestviewTestDetailss() {
			int centerId=1500;
			String testName="Lungs Profiles";
			assertThrows(DiagnosticTestNotFoundException.class,()->dcs.viewTestDetails(centerId, testName));
		}
		
	// Test For getListOfAppointments Method
	@Test
	void testGetAllOfAppointments() {
		int x=3;
		String centerName="MeryDcenter";
		List<Appointment> list=dcs.getListOfAppointments(centerName);
		assertEquals(list.size(),x);
	}
		
	
	

}
