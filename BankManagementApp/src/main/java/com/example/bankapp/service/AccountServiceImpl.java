package com.example.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository repo;
	

	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
	Account account_saved=repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
	Optional<Account> account=	repo.findById(accountNumber);
		// TODO Auto-generated method stub
	if(account.isEmpty())
	{
		throw new RuntimeException("Account is not present");
	}
	Account account_found=account.get();
		return account_found;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		// TODO Auto-generated method stub
		List<Account> ListOfAccounts= repo.findAll();
		return ListOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		// TODO Auto-generated method stub
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty())
		{
			throw new RuntimeException("Account is not prsent");
		}
		Account accountPresent = account.get();
		Double totalBalance = accountPresent.getAccount_balance()+amount;
		accountPresent.setAccount_balance(totalBalance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		// TODO Auto-generated method stub
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty())
		{
			throw new RuntimeException("Account is not prsent");
		}
		Account accountPresent = account.get();
		
		Double accountBalance=accountPresent.getAccount_balance()-amount;
		accountPresent.setAccount_balance(accountBalance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		// TODO Auto-generated method stub
		getAccountDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);
		
	}

}
