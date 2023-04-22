package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entities.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
    
}
