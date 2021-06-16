package com.health.boot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.health.boot.entities.User;
import com.health.boot.exceptions.UserAlreadyExistException;
import com.health.boot.exceptions.UserIdPasswordInvalidException;
import com.health.boot.exceptions.UserNotFoundException;
import com.health.boot.repo.UserRepository;

@Service
public class IUserServiceImpl implements IUserService {
	
	@Autowired
	UserRepository ur;

	@Override
	public User validateUser(String username, String password) throws RuntimeException {
		
		Optional<User> u = ur.findById(username);
		if(!u.isPresent())
			throw new UserNotFoundException("User Not Found. You can SignUp");
		
		if(password.equals(u.get().getPassword()))
			return u.get();
		throw new UserIdPasswordInvalidException("Password is Incorrect");

		}




	@Override
	public User addUser(User user) {
		
		Optional<User> u = ur.findById(user.getUsername());
		if(u.isPresent())
			throw new UserAlreadyExistException("Already an Account Exist with this user name");
		User uu = ur.save(user);

		return uu;
	
	}

	@Override
	public User removeUser(User user) {
		Optional<User> u = ur.findById(user.getUsername());
		if(!u.isPresent())
			throw new UserNotFoundException("User is Not Found to Delete");
		ur.delete(user);
		return user;
	}

}
