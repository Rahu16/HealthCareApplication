package com.health.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.boot.entities.DiagnosticCenter;

@Repository
public interface DiagnosticCenterRepository extends JpaRepository<DiagnosticCenter,Integer>
{

}
