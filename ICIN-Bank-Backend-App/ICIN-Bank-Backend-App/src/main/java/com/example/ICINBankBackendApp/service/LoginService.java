package com.example.ICINBankBackendApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ICINBankBackendApp.entity.Account;
import com.example.ICINBankBackendApp.entity.Login;
import com.example.ICINBankBackendApp.repository.AccountRepository;
import com.example.ICINBankBackendApp.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	LoginRepository loginRepository;
	
	public String signIn(Login login) {

		Optional<Login> result = loginRepository.findLoginUsingEmail(login.getEmailId());

		if (result.isPresent()) {
			Login ll = result.get();
			if (ll.getPassword().equals(login.getPassword())) {
				if (ll.getTypeOfUser().equals("admin")) {
					return "Admin success";
				} else {
					return "Customer success";
				}
			} else {
				return "Invalid password";
			}
			
		} else {
			return "Invalid email id";
		}
	}

	@Autowired
	AccountRepository accountRepository;
	
	public String signUp(Login login) {
		Optional<Login> result = loginRepository.findLoginUsingEmail(login.getEmailId());
		if (result.isPresent()) {
			return "Account already exists";
		} else if (login.getTypeOfUser().equals("admin")) {
			return "Admin account, Cant create";
		} else {
			loginRepository.save(login);
			
			Account acc = new Account();
			acc.setEmailid(login.getEmailId());
			acc.setTypeofaccount("savings");
			acc.setAmount(10000);
			
			accountRepository.save(acc);
			
			return "Account created successfully";
		}
	}	
	
}
