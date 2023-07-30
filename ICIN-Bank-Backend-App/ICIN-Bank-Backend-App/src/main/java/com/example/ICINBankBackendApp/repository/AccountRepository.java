package com.example.ICINBankBackendApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ICINBankBackendApp.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository <Account, Integer>{
	//JPQL 
	@Query(value="SELECT id, accno, amount, emailid, typeofaccount FROM account WHERE emailid = :emailid ", nativeQuery = true)	
	public Account findAccountByEmailId(@Param("emailid") String emailid);

	@Query(value="SELECT id, accno, amount, emailid, typeofaccount FROM account WHERE accno = :accno ", nativeQuery = true)	
	public Optional<Account> findByAccNo(@Param("accno") int accno);
	
}
