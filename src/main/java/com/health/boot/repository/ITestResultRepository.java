package com.health.boot.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.boot.entities.TestResult;

@Repository
public interface ITestResultRepository extends JpaRepository<TestResult,Integer>{


}

