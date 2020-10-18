package com.sportyshoes.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sportyshoes.ecommerce.entities.User;
import com.sportyshoes.ecommerce.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByusername(username);
		CustomUserDetail userDetails=null;
		if(user!=null){
			userDetails=new CustomUserDetail();
			userDetails.setUser(user);
		}else{
			throw new UsernameNotFoundException
			("User does not exist with username :"+username);
		}
		return userDetails;

	}

}
