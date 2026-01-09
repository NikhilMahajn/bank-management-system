package com.example.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeRequestDto {
	private Long id;

	@NotBlank
	private String employeeName;

	@Email
	private String email;

	@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid mobile number format")
	private String mobileNumber;

	@NotBlank
	private String branchCode;
}
