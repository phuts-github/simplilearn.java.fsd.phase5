package com.example.ICINBankBackendApp;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.ICINBankBackendApp.entity.Login;
import com.example.ICINBankBackendApp.repository.LoginRepository;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com.example.ICINBankBackendApp.entity")
@EnableJpaRepositories(basePackages = "com.example.ICINBankBackendApp.repository")
public class IcinBankBackendAppApplication {

	@Autowired
	LoginRepository loginRepository;

	@PostConstruct
	public void init() {
		String adminEmail = "admin@gmail.com";
		Optional<Login> result = loginRepository.findLoginUsingEmail(adminEmail);
		
		if (!result.isPresent()) {

			System.out.println("init..");
			Login ll = new Login();
			ll.setEmailId("admin@gmail.com");
			ll.setPassword("admin123");
			ll.setTypeOfUser("admin");
			loginRepository.save(ll);
			System.out.println("Admin account created..");
		} else {
			System.out.println("Admin account already exist..");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(IcinBankBackendAppApplication.class, args);
	}

}
