package com.example.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	boolean existsByEmail(String email);

	boolean existsByMobileNumber(String mobileNumber);

}
