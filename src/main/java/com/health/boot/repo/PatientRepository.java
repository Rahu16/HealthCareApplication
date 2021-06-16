package com.health.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.health.boot.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	@Query("SELECT p FROM Patient p WHERE p.name = ?1")
	Patient findPatientByName(String patientName);
}
