package com.example.bank.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String customerName;

	@Column(unique = true,nullable = false)
	private String mobileNumber;

	@Column(unique = true)
	private String email;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Account account;


}
