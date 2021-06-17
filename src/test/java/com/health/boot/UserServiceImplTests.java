package com.health.boot;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.health.boot.entities.User;
import com.health.boot.exceptions.UserAlreadyExistException;
import com.health.boot.exceptions.UserIdPasswordInvalidException;
import com.health.boot.exceptions.UserNotFoundException;
import com.health.boot.services.IUserService;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserServiceImplTests {
	
	@Autowired
	private IUserService usi;

	@Test
	void testValidateUser() {

		User u1 = new User();
		u1.setId(1000);
		u1.setUsername("HMONDAL");
		u1.setPassword("lotOfEffort");
		u1.setRole("Admin");
		User u = usi.validateUser(u1.getUsername(),u1.getPassword());
		assertEquals(u.toString(),u1.toString());
	}
	
	@Test
	void testAddUser() {

		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("hasibul");
		u1.setPassword("vaccine");
		u1.setRole("Customer");
		User u = usi.addUser(u1);
		assertTrue(u.toString().equals(u1.toString()));
	}
	
	@Test
	void testAddUserAlreadyExistException() {
		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("hasibul");
		u1.setPassword("vacci");
		u1.setRole("Customer");
		assertThrows(UserAlreadyExistException.class,()->usi.addUser(u1),"testAddUserAlreadyExistException() should throw exception");
	}

	
	@Test
	void testValidateUserIdPasswordException() {
		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("hasibul");
		u1.setPassword("vacci");
		u1.setRole("Customer");
		assertThrows(UserIdPasswordInvalidException.class,()->usi.validateUser(u1.getUsername(), u1.getPassword()),"testValidateUserIdPasswordException() should throw exception");
	}
	
	@Test
	void testRemoveUser() {
		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("hasibul");
		u1.setPassword("vaccine");
		u1.setRole("Customer");
		User u = usi.removeUser(u1);
		assertTrue(u.toString().equals(u1.toString()));
	}
	
	@Test
	void testValidateUserNotFoundException() {
		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("hasibul");
		u1.setPassword("vaccine");
		u1.setRole("Customer");
		assertThrows(UserNotFoundException.class,()->usi.validateUser(u1.getUsername(),u1.getPassword()),"testValidateUserNotFoundException() method should throw Exception");
	}
	
	@Test
	void testRemoveUserNotFoundException() {
		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("hasibul");
		u1.setPassword("vaccine");
		u1.setRole("Customer");
		assertThrows(UserNotFoundException.class,()->usi.removeUser(u1),"testRemoveUserNotFoundException() method should throw Exception");
	}
	

	
}
