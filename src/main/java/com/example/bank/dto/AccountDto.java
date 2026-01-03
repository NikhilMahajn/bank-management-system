package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
	private Long id;

	private String accountNumber;

	private Long balance;

	private boolean isActive;

	private CustomerDto customer;
}
