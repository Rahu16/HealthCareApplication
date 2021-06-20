package com.health.boot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.health.boot.entities.User;
import com.health.boot.exceptions.UserAlreadyExistException;
import com.health.boot.exceptions.UserIdPasswordInvalidException;
import com.health.boot.exceptions.UserNotFoundException;
import com.health.boot.repository.UserRepository;

@Service
public class IUserServiceImpl implements IUserService 
{
	
	@Autowired
	UserRepository ur;
	
	
	/* This method validates whether the entered username and password are correct or not.
	 *  If the username is already present in database then it throws an UserNotFoundException.
	 */
	@Override
	public User validateUser(String username, String password) throws RuntimeException 
	{
		
		Optional<User> u = ur.findById(username);
		if(!u.isPresent())
			throw new UserNotFoundException("User Not Found. You can SignUp");
		
		if(password.equals(u.get().getPassword()))
			return u.get();
		throw new UserIdPasswordInvalidException("Password is Incorrect");

		}
	
	
	/*  This method is used to create new user
	 *  If there is already a username present then it throws an UserAlreadyExistException
	 */
	@Override
	public User addUser(User user) 
	{
		Optional<User> u = ur.findById(user.getUsername());
		if(u.isPresent())
			throw new UserAlreadyExistException("Already an Account Exist with this user name");
		User uu = ur.save(user);
		return uu;
	}
	
	
	/*  This method is used to delete the user
	 *  if the username is not registered then it throws an UserNotFoundException
	 */
	@Override
	public User removeUser(User user) 
	{
		Optional<User> u = ur.findById(user.getUsername());
		if(!u.isPresent())
			throw new UserNotFoundException("User is Not Found to Delete");
		ur.delete(user);
		return user;
	}
	}
