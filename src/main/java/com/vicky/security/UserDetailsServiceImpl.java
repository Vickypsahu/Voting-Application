package com.vicky.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vicky.model.User;
import com.vicky.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUsername(username);
		System.out.println(user.getEmail());
		if(user!=null)
		{
			return new UserDetailsImpl(user);
		}
		throw new UsernameNotFoundException("Username does not exists!");
	
	}

}
