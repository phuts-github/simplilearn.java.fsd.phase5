package com.example.ICINBankBackendApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ICINBankBackendApp.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository <Login, String>{

	@Query(value="SELECT id, email_id, password, type_of_user FROM login WHERE email_id = :emailid ", nativeQuery = true)	
	Optional<Login> findLoginUsingEmail(@Param("emailid") String emailid);

}
