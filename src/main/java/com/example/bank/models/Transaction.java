package com.example.bank.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Transaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sender_id")
	private Account senderAccount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="reciever_id")
	private Account recieverAccount;

	private Long amount;

	@Enumerated(EnumType.STRING)
	private TransactionStatus status;
}
