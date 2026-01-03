package com.example.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

	private Long id;

	@NotBlank
	private String customerName;

	@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid mobile number format")
	private String mobileNumber;

	@Email
	private String email;

	@NotBlank
	private String password;


	public CustomerDto(Long id,String customerName, String mobileNumber, String email) {
		this.id = id;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
}
