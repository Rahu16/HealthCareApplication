package com.health.boot;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.html.parser.DTD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.health.boot.entities.DiagnosticTest;
import com.health.boot.exceptions.AllreadyDiagnosticTestExistException;
import com.health.boot.exceptions.DiagnosticCenterNotFoundException;
import com.health.boot.exceptions.DiagnosticTestNotFoundException;
import com.health.boot.repository.DiagnosticTestRepository;
import com.health.boot.services.IDiagnosticTestService;
@SpringBootTest
class DiagnosticTestJunitTest {

	@Autowired
	IDiagnosticTestService dts;
	
	@Autowired
	DiagnosticTestRepository dtr;
	
	// Test For  getAllTest Method
	@Test
	void test() {
		int l1=dts.getAllTest().size();
		int l2=dtr.findAll().size();
		assertEquals(l1,l2);
	}
	
	// Test For CreateNewTest Method
//	@Test
//	void testCreateNewTest() {
//		DiagnosticTest dt= new DiagnosticTest();
//		dt.setId(111);
//		dt.setNormalValue("med");
//		dt.setTestName("Bronchoscopy1");
//		dt.setUnits("M");
//		dt.setTestPrice(3500);
//		DiagnosticTest dt1=dts.CreateNewTest(dt);
	
//		assertEquals(dt.toString(),dt1.toString());
//	}
	
	// Test For AllreadyDiagnosticTestExistException in CreateNewTest Method
	@Test
	void exceptionTestCreateNewTest() {
		DiagnosticTest dt= new DiagnosticTest();
		dt.setId(155);
		dt.setNormalValue("med");
		dt.setTestName("Bronchoscopy1");
		dt.setUnits("M");
		dt.setTestPrice(3500);
		
		assertThrows(AllreadyDiagnosticTestExistException.class,()->dts.CreateNewTest(dt));
	}
	
	// Test For updateTestDetail Method
	@Test
	void testUpdateTestDetail() {
		DiagnosticTest dt= new DiagnosticTest();
		dt.setId(121);
		dt.setNormalValue("med");
		dt.setTestName("Bronchoscopy1");
		dt.setUnits("M");
		dt.setTestPrice(5500);
		DiagnosticTest dt1=dts.updateTestDetail(dt);
		assertEquals(dt1.toString(),dt.toString());
		
	}
	
	// Test For DiagnosticTestNotFoundException in updateTestDetail Method
	@Test
	void exceptionTestUpdateTestDetail() {
		DiagnosticTest dt= new DiagnosticTest();
		dt.setId(141);
		dt.setNormalValue("med");
		dt.setTestName("Bronchoscopy1");
		dt.setUnits("M");
		dt.setTestPrice(5500);
		assertThrows(DiagnosticTestNotFoundException.class,()->dts.updateTestDetail(dt));
	}
	
	// Test For testById Method
	@Test
	void testTestById() {
		int testId=121;
		DiagnosticTest dt=dts.testById(testId);
		assertEquals(dt.getId(),testId);
	}
	
	// Test For DiagnosticTestNotFoundException in testById Method
	@Test
	void exceptionTestById() {
		int testId=100;
		assertThrows(DiagnosticTestNotFoundException.class,()->dts.testById(testId));
	}
	
	// Test For removeTestFromDiagnosticCenter Method
//	@Test
//	void testRemoveTestFromDiagnosticCenter() {
//		int centerId=1221;
//		int testId=143;
//		DiagnosticTest dt=dts.removeTestFromDiagnosticCenter(centerId, testId);
//		assertEquals(dt.getId(),testId);
//	}
	
	// Test For DiagnosticCenterNotFoundException in removeTestFromDiagnosticCenter Method
	@Test
	void exceptionRemoveTestFromDiagnosticCenter() {
		int centerId=1000;
		int testId=143;
		assertThrows(DiagnosticCenterNotFoundException.class,()->dts.removeTestFromDiagnosticCenter(centerId, testId));
	}
	
	// Test For DiagnosticTestNotFoundException1 in  removeTestFromDiagnosticCenter Method
	@Test 
	void exception1RemoveTestFromDiagnosticCenter() {
		int centerId=1221;
		int testId=100;
		assertThrows(DiagnosticTestNotFoundException.class,()->dts.removeTestFromDiagnosticCenter(centerId, testId));
	}
	
	// Test For DiagnosticTestNotFoundException2 in  removeTestFromDiagnosticCenter Method
	@Test 
	void exception2RemoveTestFromDiagnosticCenter() {
		int centerId=1221;
		int testId=108;
		assertThrows(DiagnosticTestNotFoundException.class,()->dts.removeTestFromDiagnosticCenter(centerId, testId));
	}
	
	// Test For getTestsOfDiagnosticCenter Method
	@Test
	void testGetTestsOfDiagnosticCenter() {
		int centerId=1221;
		int l1=2;
		int l2=dts.getTestsOfDiagnosticCenter(centerId).size();
		assertEquals(l2,l1);
	}
	
	// Test For DiagnosticCenterNotFoundException in getTestsOfDiagnosticCenter Method
	@Test
	void exceptionTestGetTestsOfDiagnosticCenter() {
		
		int centerId=1222;
		assertThrows(DiagnosticCenterNotFoundException.class, ()->dts.getTestsOfDiagnosticCenter(centerId));
	}

}
