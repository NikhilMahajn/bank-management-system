package com.example.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.dto.AccountDto;
import com.example.bank.exceptions.ResourceNotFoundException;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.models.Account;
import com.example.bank.repositories.AccountRepository;

@Service
public class AccountService {
	
	private AccountRepository accountRepository;

	private AccountMapper accountMapper;

	public AccountService(AccountRepository accountRepository,AccountMapper accountMapper){
		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
	}

	public List<AccountDto> getAllAccounts(){
		return accountRepository.findAll()
				.stream().map(accountMapper::toDto).toList();
	}
	
	public AccountDto getAccount(Long accountId) {

		Account account = accountRepository.findById(accountId)
			.orElseThrow(() ->
				new ResourceNotFoundException("Account not found with id: " + accountId)
			);

		return accountMapper.toDto(account);
	}


}
