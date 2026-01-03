package com.example.bank.mapper;

import org.springframework.stereotype.Component;

import com.example.bank.dto.CustomerDto;
import com.example.bank.models.Customer;

@Component
public class CustomerMapper {
	
	public CustomerDto toDto(Customer customer) {
			return new CustomerDto(
				customer.getId(),
				customer.getCustomerName(),
				customer.getMobileNumber(),
				customer.getEmail()
			);
			 
    }
	public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setCustomerName(dto.getCustomerName());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setEmail(dto.getEmail());
        return customer;
    }



}
