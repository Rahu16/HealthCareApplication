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
public class UserServiceImplTests 
{
	
	@Autowired
	private IUserService usi;

	// Test for Validating User
	@Test
	void testValidateUser() 
	{
		User u1 = new User();
		u1.setId(1);
		u1.setUsername("satish");
		u1.setPassword("satish143");
		u1.setRole("patient");
		User u = usi.validateUser(u1.getUsername(),u1.getPassword());
		assertEquals(u.toString(),u1.toString());
	}
	
	// Test for Adding User
	@Test
	void testAddUser() 
	{
		User u1 = new User();
		u1.setId(7);
		u1.setUsername("bharath");
		u1.setPassword("mahesh");
		u1.setRole("Admin");
		User u = usi.addUser(u1);
		assertTrue(u.toString().equals(u1.toString()));
	}
	
	// Test for AddUserAlreadyExistException
	@Test
	void testAddUserAlreadyExistException() 
	{
		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("satish");
		u1.setPassword("satish143");
		u1.setRole("patient");
		assertThrows(UserAlreadyExistException.class,()->usi.addUser(u1),"testAddUserAlreadyExistException() should throw exception");
	}
	
	// Test for ValidateUserIdPasswordException
	@Test
	void testValidateUserIdPasswordException() 
	{
		User u1 = new User();
		u1.setId(205);
		u1.setUsername("mahesh");
		u1.setPassword("vacci");
		u1.setRole("Admin");
		assertThrows(UserIdPasswordInvalidException.class,()->usi.validateUser(u1.getUsername(), u1.getPassword()),"testValidateUserIdPasswordException() should throw exception");
	}
	
	// Test for Removing User
	@Test
	void testRemoveUser() 
	{
		User u1 = new User();
		u1.setId(205);
		u1.setUsername("mahesh");
		u1.setPassword("mahesh");
		u1.setRole("Admin");
		User u = usi.removeUser(u1);
		assertTrue(u.toString().equals(u1.toString()));
	}
	
	// Test for ValidateUserNotFoundException
	@Test
	void testValidateUserNotFoundException() 
	{
		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("hasibul");
		u1.setPassword("vaccine");
		u1.setRole("Customer");
		assertThrows(UserNotFoundException.class,()->usi.validateUser(u1.getUsername(),u1.getPassword()),"testValidateUserNotFoundException() method should throw Exception");
	}
	
	// Test for RemoveUserNotFoundException
	@Test
	void testRemoveUserNotFoundException() 
	{
		User u1 = new User();
		u1.setId(2000);
		u1.setUsername("hasibul");
		u1.setPassword("vaccine");
		u1.setRole("Customer");
		assertThrows(UserNotFoundException.class,()->usi.removeUser(u1),"testRemoveUserNotFoundException() method should throw Exception");
	}		
}
