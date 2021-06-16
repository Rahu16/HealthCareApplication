package com.health.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.boot.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	
}
