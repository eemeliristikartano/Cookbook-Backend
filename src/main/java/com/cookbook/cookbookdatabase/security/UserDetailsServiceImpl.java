package com.cookbook.cookbookdatabase.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cookbook.cookbookdatabase.domain.User;
import com.cookbook.cookbookdatabase.domain.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	/*
	 * Provide user information.
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);
		UserBuilder userBuilder = null;
		if (user.isPresent()) {
			User currUser = user.get();
			userBuilder = 
					org.springframework.security.core.userdetails.User.withUsername(username);
			userBuilder.password(currUser.getPassword());
			userBuilder.roles(currUser.getRole());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		return userBuilder.build();
	}

}
