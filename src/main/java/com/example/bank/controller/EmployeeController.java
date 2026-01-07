package com.example.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.CustomerDto;
import com.example.bank.dto.EmployeeDto;
import com.example.bank.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add-employee")
	public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {

		return employeeService.createEmployee(employeeDto);
		
	}
	


}
