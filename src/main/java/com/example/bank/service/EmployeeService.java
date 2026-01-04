package com.example.bank.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bank.dto.CustomerDto;
import com.example.bank.dto.EmployeeDto;
import com.example.bank.mapper.EmployeeMapper;
import com.example.bank.models.Employee;
import com.example.bank.models.Role;
import com.example.bank.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	private EmployeeMapper employeeMapper;

	private BankBranchService bankBranchService;

	private JwtService jwtService;

	private PasswordEncoder passwordEncoder;

	public EmployeeService(
			EmployeeRepository employeeRepository,
			EmployeeMapper employeeMapper,
			BankBranchService bankBranchService,
			JwtService jwtService,
			PasswordEncoder passwordEncoder
		
		){

		this.employeeMapper = employeeMapper;
		this.employeeRepository = employeeRepository;
		this.bankBranchService = bankBranchService;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public EmployeeDto createEmployee(EmployeeDto request) {
		Employee employee = employeeMapper.toEntity(request);

		bankBranchService.addEmployeeToBranch(1L, employee);

		employeeRepository.save(employee);

		String pass = passwordEncoder.encode("Pass@123");

		jwtService.addUser(employee.getEmail(), Role.ROLE_EMPLOYEE, employee.getId(),pass);

		return employeeMapper.toDto(employee);

	}



}
