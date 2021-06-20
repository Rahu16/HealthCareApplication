package com.health.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.boot.entities.Appointment;


/* Repository method declared here which is extending JpaRepository
 * for making the CRUD Operations.
 * Instead of CRUD Repository we used here JpaRepository to get some 
 * more additional functionalities
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>
{

}
