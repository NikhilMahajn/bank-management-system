package com.example.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.AccountDto;
import com.example.bank.service.AccountService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService){
		this.accountService = accountService;
	}
	
	@GetMapping("/get-accounts")
	public List<AccountDto> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/get-account/{account_id}")
	public AccountDto getAccount(@PathVariable Long account_id) {
		return accountService.getAccount(account_id);
	}
	
}
