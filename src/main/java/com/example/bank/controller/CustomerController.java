package com.example.bank.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.CustomerDto;
import com.example.bank.service.CustomerService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;






@RestController 
@RequestMapping("/customer")
public class CustomerController{
	public CustomerService customerService;

	public CustomerController(CustomerService customerService){
		this.customerService = customerService;
	}

	@PostMapping("/add-customer")
	public CustomerDto addCustomer(@Valid @RequestBody CustomerDto customer) {
		return customerService.createCustomer(customer);
		
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/get-customers")
	public List<CustomerDto> getCustomer() {
		return customerService.getAllCustomers();
	}
	
	
	
	
	
}