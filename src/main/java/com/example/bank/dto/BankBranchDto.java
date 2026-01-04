package com.example.bank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BankBranchDto {

	private Long id;
	@NotBlank
	private String branchName;

	private String branchCode;

	@NotBlank
	private String city;

}
