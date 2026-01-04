package com.example.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String employeeName;

	@Column(nullable = false,unique = true)
	private String email;

	@Column(nullable = false,unique = true)
	private String mobileNumber;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankBranch_id", nullable = false)
	private BankBranch bankBranch;

	


}
