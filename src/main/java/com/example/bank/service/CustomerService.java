package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bank.dto.CustomerDto;
import com.example.bank.exceptions.DuplicateResourceException;
import com.example.bank.mapper.CustomerMapper;
import com.example.bank.models.Account;
import com.example.bank.models.Customer;
import com.example.bank.models.Role;
import com.example.bank.models.User;
import com.example.bank.repositories.CustomerRepository;
import com.example.bank.repositories.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	private final CustomerMapper customerMapper;

	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper,UserRepository userRepository){
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
		this.userRepository = userRepository;
	}

	@Transactional
	public CustomerDto createCustomer(@Valid CustomerDto customerRequest){
	
		Customer customer = customerMapper.toEntity(customerRequest);

		Account account = new Account();
		account.setActive(true);
		account.setCustomer(customer);

		customer.setAccount(account);
		customerRepository.save(customer);

		String accountNumber = generateAccountNumber(account.getId());
        account.setAccountNumber(accountNumber);


		User user = new User();
		user.setUsername(customerRequest.getEmail());
		user.setReferenceId(customer.getId());
		user.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
		user.setRole(Role.ROLE_CUSTOMER);
		
		userRepository.save(user);

		return customerMapper.toDto(customer);
		
	}

	private String generateAccountNumber(Long id) {
        return "BANK" + String.format("%010d", id);
    }
	
	public List<CustomerDto> getAllCustomers(){
		return customerRepository.findAll()
				.stream().map(customerMapper::toDto).toList();
	}
}
