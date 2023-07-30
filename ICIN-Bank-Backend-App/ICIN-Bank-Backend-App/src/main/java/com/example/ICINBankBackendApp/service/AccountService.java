package com.example.ICINBankBackendApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ICINBankBackendApp.entity.Account;
import com.example.ICINBankBackendApp.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public String createAccount(Account account) {
		accountRepository.save(account);
		return "Account created successfully";
	}
	
	public Account findAccountDetails(int accno) {
		Optional<Account> result = accountRepository.findById(accno);
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	// this method we will call in customer dashboard in angular 
	public Account findAccountDetails(String emailid) {
		return accountRepository.findAccountByEmailId(emailid); // calling custom method 
	}
	
	// admin can view all account details. 
	public List<Account> findAllAccounts() {
		return accountRepository.findAll();
	}
	// accno 1, amount : 1000	we are planning transfer accno 1 to 2 200 
	// accno 2, amount : 500 
	public String transferAmount(int toAccno, int fromAccno,float amount, String emalid) {
		Optional<Account> toAccount = accountRepository.findById(toAccno);	//2
		Optional<Account> fromAccount = accountRepository.findById(fromAccno);	//1
		if(fromAccount == null) {
			return "From account number is invalid";
		}else if(toAccount == null){
			return "To account number is invalid";
		}else {
			Account fromAccountDetails = fromAccount.get();	// 1, amount : 1000
			Account toAccountDetails = toAccount.get();		// 2 , amount : 500 	
			
			toAccountDetails.setAmount(toAccountDetails.getAmount()-amount);	// 800
			fromAccountDetails.setAmount(fromAccountDetails.getAmount()+amount);	//700
			accountRepository.saveAndFlush(fromAccountDetails);
			accountRepository.saveAndFlush(fromAccountDetails);
			// save this information in transaction table 
			// tid(auto increment), emaliid, from acno, to accno amount, dateandtime;
			//LocalDateTime.now();
			return "Amount transfer successfully";
		}
	}
}
