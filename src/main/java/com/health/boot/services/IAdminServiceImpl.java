package com.health.boot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.boot.entities.User;
import com.health.boot.exceptions.UserNotFoundException;
import com.health.boot.repository.UserRepository;

@Service
public class IAdminServiceImpl implements IAdminService
{

	@Autowired
	IUserServiceImpl usi;
	
	@Autowired
	UserRepository ur;
	
	@Override
	public void registerAdmin(String username, String password) throws RuntimeException 
	{
		Optional<User> u = ur.findById(username);
		if(u.get().getRole()=="Admin") 
		{
			usi.addUser(new User(10,username,password,"Admin"));
			return;
		}
		
		throw new UserNotFoundException("Admin is Not Found with this User Name: "+username);
		
	}
}
