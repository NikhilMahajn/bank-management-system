package com.example.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.AccountDto;
import com.example.bank.dto.AccountStatDto;
import com.example.bank.dto.ApiResponse;
import com.example.bank.dto.TransferDto;
import com.example.bank.dto.WithdrawResponse;
import com.example.bank.service.AccountService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService){
		this.accountService = accountService;
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@GetMapping("/get-accounts")
	public List<AccountDto> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@GetMapping("/get-account/{account_id}")
	public AccountDto getAccount(@PathVariable Long account_id) {
		return accountService.getAccount(account_id);
	}

	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@GetMapping("/get-account")
	public AccountDto getAccountByUsernamehandler() {
		return accountService.getAccountByUsername();
	}

	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@PostMapping("/deposite")
	public AccountDto depositMoney(@Valid @RequestBody AccountDto request) {
		
		return accountService.deposite(request);
	}

	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@PostMapping("/withdraw")
	public WithdrawResponse postMethodName(@RequestBody AccountDto request) {
		
		return accountService.withdraw(request);
	}

	@PostMapping("/transfer")
	public ApiResponse transfer(@RequestBody TransferDto transferRequest) {
		return accountService.transferMoney(transferRequest);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	@GetMapping("/stats")
	public AccountStatDto getAccountStatsHandler() {
		return accountService.getAccountStats();
	}


	
		
	
}
