package com.health.boot.services;

import com.health.boot.controllers.ObjHolder;
import com.health.boot.entities.User;

public interface IUserService {
	
	User validateUser(String username, String password) throws RuntimeException;
	public User addUser(User user);
	public User removeUser(ObjHolder obj);

}
