package com.example.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.CustomerDto;
import com.example.bank.dto.EmployeeDto;
import com.example.bank.dto.EmployeeRequestDto;
import com.example.bank.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
	

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add-employee")
	public EmployeeDto addEmployee(@RequestBody EmployeeRequestDto employeeDto) {

		return employeeService.createEmployee(employeeDto);
		
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get-employees")
	public List<EmployeeDto> getEmployees() {
		return employeeService.getAllEmployee();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		employeeService.removeEmployee(id);
		return ResponseEntity.ok("Employee removed successfully");
	}

	
	

}
