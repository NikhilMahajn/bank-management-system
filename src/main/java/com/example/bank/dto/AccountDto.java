package com.example.bank.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
	private Long id;

	private String accountNumber;

	@Min(value = 1)
	private Long balance;

	private boolean isActive;

	private CustomerDto customer;
}
