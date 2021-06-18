package com.health.boot.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.health.boot.entities.User;
import com.health.boot.exceptions.UserAlreadyExistException;
import com.health.boot.exceptions.UserIdPasswordInvalidException;
import com.health.boot.services.IUserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	IUserService uService;
	
	@PostMapping("/login")
	public ResponseEntity<String> signIn(@RequestBody ObjHolder obj)
	{	
		String username = obj.getUsername();
		String password = obj.getPassword();
		User u = uService.validateUser(username, password);
		return new ResponseEntity<String>("Logged In",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/signUp")
	public ResponseEntity<String> signUp(@RequestBody User user){
		User u = uService.addUser(user);
		return new ResponseEntity<String>("Sign Up Sucessfull",HttpStatus.ACCEPTED);

	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestBody ObjHolder obj){
		uService.removeUser(obj);
		return new ResponseEntity<String>("User is Deleted",HttpStatus.ACCEPTED);

	}
	
	

}
