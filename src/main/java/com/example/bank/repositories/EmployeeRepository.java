package com.example.bank.repositories;

import com.example.bank.models.Customer;
import com.example.bank.models.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
