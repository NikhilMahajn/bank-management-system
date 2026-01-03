package com.example.bank.service;

import org.springframework.stereotype.Service;

import com.example.bank.dto.CustomerDto;
import com.example.bank.mapper.EmployeeMapper;
import com.example.bank.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	private EmployeeMapper employeeMapper;

	public EmployeeService(EmployeeRepository employeeRepository,EmployeeMapper employeeMapper){
		this.employeeMapper = employeeMapper;
		this.employeeRepository = employeeRepository;
	}



}
