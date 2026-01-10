package com.example.bank.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BankBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String branchName;

	@Column(nullable = false,unique = true)
	private String branchCode;

	@Column(nullable = false)
	private String city;

	@OneToMany(
		mappedBy = "bankBranch",
        cascade = CascadeType.ALL,
        orphanRemoval = true
	)
	private List<Employee> employee = new ArrayList<>();

	@OneToMany(
		mappedBy = "bankBranch",
        cascade = CascadeType.ALL,
        orphanRemoval = true
	)
	private List<Account> accounts = new ArrayList<>();


	public void addEmployee(Employee newEmployee) {
		employee.add(newEmployee);
		newEmployee.setBankBranch(this);
	}

	public void removeEmployee(Employee newEmployee) {
		employee.remove(employee);
		newEmployee.setBankBranch(null);
	}

	public void addAccount(Account newAccount) {
		accounts.add(newAccount);
		newAccount.setBankBranch(this);
	}

	public void removeAccount(Account newAccount) {
		employee.remove(newAccount);
		newAccount.setBankBranch(null);
	}


}
