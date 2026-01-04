package com.example.bank.mapper;

import org.springframework.stereotype.Component;
import com.example.bank.controller.AccountController;
import com.example.bank.dto.AccountDto;
import com.example.bank.models.Account;

@Component
public class AccountMapper {

	private final CustomerMapper customerMapper = new CustomerMapper();
	
	public AccountDto toDto(Account account){
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setBalance(account.getBalance());
		accountDto.setActive(account.isActive());
		accountDto.setId(account.getId());
		accountDto.setCustomer(customerMapper.toDto(account.getCustomer()));

		return accountDto;
	}
}
