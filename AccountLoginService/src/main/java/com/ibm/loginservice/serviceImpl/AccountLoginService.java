package com.ibm.loginservice.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.loginservice.domain.User;
import com.ibm.loginservice.dto.UserDTO;
import com.ibm.loginservice.repository.AccountLoginRepository;

@Service
public class AccountLoginService implements UserDetailsService{
	@Autowired
	AccountLoginRepository accountLoginRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = accountLoginRepository.findByusername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public UserDTO getUser(UserDTO userDTO) {
		User aUser = accountLoginRepository.findByusername(userDTO.getUsername());

		if (aUser != null) {
			UserDTO dto = new UserDTO();
			dto.setUsername(aUser.getUsername());
			dto.setPassword(aUser.getPassword());

			return dto;
		}
		else {
			return null;
		}
	}
	
	public UserDTO getUserDetailsById(Long id) {
		Optional<User> user = accountLoginRepository.findById(id);
		UserDTO dto = new UserDTO();
		if(user.isPresent()) {
			dto.setUsername(user.get().getUsername());
			dto.setPassword(user.get().getPassword());
			return dto;
		}
		return dto;
	}
}
