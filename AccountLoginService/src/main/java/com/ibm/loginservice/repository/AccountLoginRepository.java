package com.ibm.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.loginservice.domain.User;

@Repository
public interface AccountLoginRepository extends JpaRepository<User, Long>{
	User findByusername(String username);
}
