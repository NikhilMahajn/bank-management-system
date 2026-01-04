package com.example.bank.mapper;

import org.springframework.stereotype.Component;

import com.example.bank.dto.EmployeeDto;
import com.example.bank.models.Employee;

@Component
public class EmployeeMapper {
	private final BankBranchMapper bankBranchMapper;

	public EmployeeMapper(BankBranchMapper bankBranchMapper){
		this.bankBranchMapper = bankBranchMapper;
	}
	public EmployeeDto toDto(Employee employee){
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmployeeName(employee.getEmployeeName());
		employeeDto.setMobileNumber(employee.getMobileNumber());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setBankBranch(bankBranchMapper.toDto(employee.getBankBranch()));

		return employeeDto;
	}

	public Employee toEntity(EmployeeDto employeeDto){
		Employee employee = new Employee();
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setMobileNumber(employeeDto.getMobileNumber());
		employee.setEmail(employeeDto.getEmail());

		return employee;

	}
}
