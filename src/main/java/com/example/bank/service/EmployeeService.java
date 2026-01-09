package com.example.bank.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bank.dto.CustomerDto;
import com.example.bank.dto.EmployeeDto;
import com.example.bank.dto.EmployeeRequestDto;
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
	public EmployeeDto createEmployee(EmployeeRequestDto request) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmployeeName(request.getEmployeeName());
		employeeDto.setEmail(request.getEmail());
		employeeDto.setMobileNumber(request.getMobileNumber());

		Employee employee = employeeMapper.toEntity(employeeDto);

		bankBranchService.addEmployeeToBranch(request.getBranchCode(), employee);

		employeeRepository.save(employee);

		String pass = passwordEncoder.encode("Pass@123");

		jwtService.addUser(employee.getEmail(), Role.ROLE_EMPLOYEE, employee.getId(),pass);

		return employeeMapper.toDto(employee);

	}

	public List<EmployeeDto> getAllEmployee(){
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map(employeeMapper::toDto).toList();
	}



}
