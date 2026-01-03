package com.example.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.models.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{
	
}
