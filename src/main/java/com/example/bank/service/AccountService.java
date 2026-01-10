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
import com.example.bank.dto.AccountStatDto;
import com.example.bank.dto.ApiResponse;
import com.example.bank.dto.TransferDto;
import com.example.bank.dto.WithdrawResponse;
import com.example.bank.exceptions.InsufficientBalanceException;
import com.example.bank.exceptions.ResourceNotFoundException;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.models.Account;
import com.example.bank.models.BankBranch;
import com.example.bank.models.Customer;
import com.example.bank.models.Employee;
import com.example.bank.models.Transaction;
import com.example.bank.models.TransactionStatus;
import com.example.bank.models.TransactionType;
import com.example.bank.models.User;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.BankBranchRepository;
import com.example.bank.repositories.CustomerRepository;
import com.example.bank.repositories.TransactionRepository;
import com.example.bank.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

    private final CustomerRepository customerRepository;
	
	private AccountRepository accountRepository;

	private AccountMapper accountMapper;

	private UserRepository userRepository;

	private TransactionRepository transactionRepository;

	@Autowired
	private EmployeeService employeeService;

	public AccountService(AccountRepository accountRepository,
						AccountMapper accountMapper,
						UserRepository userRepository,
						CustomerRepository customerRepository,
						TransactionRepository transactionRepository
						){

		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
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
	
	public BankBranch getAccountBranchByToken(){
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName(); 

		User user = userRepository.findByUsername(username).orElseThrow(() ->
				new ResourceNotFoundException("User not found with username: " + username));

		Employee employee = employeeService.getEmployeeById(user.getReferenceId());
		System.out.println(employee.getBankBranch().getId());
		
		return employee.getBankBranch();
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

	public AccountStatDto getAccountStats(){
		AccountStatDto accountStatDto = new AccountStatDto();

		accountStatDto.setTotalAccounts(accountRepository.count());
		accountStatDto.setActiveAccounts(accountRepository.countByIsActiveTrue());
		accountStatDto.setTotalAmount(accountRepository.sumBalanceByIsActive(true));
		accountStatDto.setTotalTransactions(transactionRepository.count());

		return accountStatDto;
	}

	public AccountStatDto getAccountStatsBranch(){
		AccountStatDto accountStatDto = new AccountStatDto();
		
		BankBranch branch = getAccountBranchByToken();
		Object[] row = accountRepository.getStatsForBranch(branch.getId()).get(0);

		accountStatDto.setTotalAccounts(((Number) row[0]).longValue());
		accountStatDto.setActiveAccounts(((Number) row[1]).longValue());
		accountStatDto.setTotalAmount(((Number) row[2]).longValue());
		accountStatDto.setTotalTransactions(transactionRepository.count());

		return accountStatDto;
	}



	@Transactional
	public ApiResponse transferMoney(TransferDto transferRequest) {
		Account sender = getAccountByToken();

		if (transferRequest.getAmount() <= 0) {
			throw new IllegalArgumentException("Amount must be greater than 0");
		}

		Account receiver = accountRepository.findByAccountNumber(transferRequest.getRecieverAccount())
				.orElseThrow(() -> new ResourceNotFoundException("Receiver account not found"));

		if (sender.getId().equals(receiver.getId())) {
			throw new IllegalArgumentException("Cannot transfer to same account");
		}

		// Create transaction
		Transaction tx = new Transaction();
		tx.setSenderAccount(sender);
		tx.setRecieverAccount(receiver);
		tx.setAmount(transferRequest.getAmount());

		// Check balance
		if (sender.getBalance() < transferRequest.getAmount()) {
			tx.setStatus(TransactionStatus.FAILED);
			transactionRepository.save(tx);
			throw new InsufficientBalanceException("Insufficient funds");
		}

		// Apply transfer
		sender.setBalance(sender.getBalance() - transferRequest.getAmount());
		receiver.setBalance(receiver.getBalance() + transferRequest.getAmount());
		tx.setStatus(TransactionStatus.SUCCESS);

		accountRepository.save(sender);
		accountRepository.save(receiver);
		transactionRepository.save(tx);

		return new ApiResponse("SUCCESS", "Money transferred successfully");
	}


	@Transactional
	public ApiResponse blockAccount(String accountNo){
		Account account = accountRepository.findByAccountNumber(accountNo).orElseThrow(
			()-> new ResourceNotFoundException("Account not found with ACN "+accountNo));
		account.setActive(false);
		accountRepository.save(account);
		return new ApiResponse("SUCCESS", accountNo+" Account Blocked Successful");
		
	}



}
