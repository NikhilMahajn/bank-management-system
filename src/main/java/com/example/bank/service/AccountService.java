package com.example.bank.service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.bank.dto.AccountDto;
import com.example.bank.dto.WithdrawResponse;
import com.example.bank.exceptions.InsufficientBalanceException;
import com.example.bank.exceptions.ResourceNotFoundException;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.models.Account;
import com.example.bank.models.Customer;
import com.example.bank.models.User;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.CustomerRepository;
import com.example.bank.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

    private final CustomerRepository customerRepository;
	
	private AccountRepository accountRepository;

	private AccountMapper accountMapper;

	private UserRepository userRepository;


	public AccountService(AccountRepository accountRepository,
						AccountMapper accountMapper,
						UserRepository userRepository,
						CustomerRepository customerRepository				
						){

		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
	}

	public List<AccountDto> getAllAccounts(){
		return accountRepository.findAll()
				.stream().map(accountMapper::toDto).toList();
	}
	
	public AccountDto getAccount(Long accountId) {

		Account account = accountRepository.findById(accountId)
			.orElseThrow(() ->
				new ResourceNotFoundException("Account not found with id: " + accountId)
			);

		return accountMapper.toDto(account);
	}

	public Account getAccountByToken(){
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName(); 

		User user = userRepository.findByUsername(username)
			.orElseThrow(() ->
				new ResourceNotFoundException("User not found with username: " + username));

		Customer customer = customerRepository.findById(user.getReferenceId())
			.orElseThrow(() ->
				new ResourceNotFoundException("User account not registered"));

		Account account = accountRepository.findByCustomer(customer)
			.orElseThrow(() ->
				new ResourceNotFoundException("User account not registered"));
		return account;
	}

	public AccountDto getAccountByUsername(){
		
		Account account = getAccountByToken();

		return accountMapper.toDto(account);
	}

	@Transactional
	public AccountDto deposite(AccountDto request){
		
		Account account = getAccountByToken();

		account.setBalance(account.getBalance() + request.getBalance());

		accountRepository.save(account);

		return accountMapper.toDto(account);

	}

	@Transactional
	public WithdrawResponse withdraw(AccountDto request){
		Account account = getAccountByToken();

		if(account.getBalance() < request.getBalance()){
			throw new InsufficientBalanceException("Insufficent Funds");
		}
		account.setBalance(account.getBalance() - request.getBalance());

		accountRepository.save(account);

		WithdrawResponse withdrawResponse = new WithdrawResponse();
		withdrawResponse.setAccountNumber(account.getAccountNumber());
		withdrawResponse.setBalance(account.getBalance());
		withdrawResponse.setWithdrawAmount(request.getBalance());
		return withdrawResponse;
	}

}
