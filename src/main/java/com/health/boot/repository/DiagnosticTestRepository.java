package com.health.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.boot.entities.DiagnosticTest;

@Repository
public interface DiagnosticTestRepository extends JpaRepository<DiagnosticTest, Integer>
{
	
}
