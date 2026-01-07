package com.example.bank.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountStatDto {
	private Long totalAccounts;

	private Long activeAccounts;

	private Long totalAmount;
}
