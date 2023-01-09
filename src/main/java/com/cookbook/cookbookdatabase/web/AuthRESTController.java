package com.cookbook.cookbookdatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookbook.cookbookdatabase.domain.LoginCredentials;
import com.cookbook.cookbookdatabase.domain.NewUserValidation;
import com.cookbook.cookbookdatabase.domain.User;
import com.cookbook.cookbookdatabase.domain.UserRepository;
import com.cookbook.cookbookdatabase.security.JWTUtil;

import jakarta.validation.Valid;

/*
 * Controller for handling logging in and registering a new account.
 */

@RestController
@CrossOrigin
public class AuthRESTController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	/*
	 * Handles saving a new user.
	 */
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> addUser(@RequestBody @Valid NewUserValidation newUser) {
		// Check, is the password same as the passwordCheck.
		if (newUser.getPassword().equals(newUser.getPasswordCheck())) {
			User user = new User();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String password = newUser.getPassword();
			// Encode the user's password.
			user.setPassword(bc.encode(password));
			// Set username
			user.setUsername(newUser.getUsername());
			// Set role.
			user.setRole("USER");
			
			// Username must be unique.
			if (userRepo.findByUsername(newUser.getUsername()).isEmpty()) {
				// Save the new user.
				userRepo.save(user);
			} else {
				// If the username is not unique -> return HttpStatus 409.
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} else {
			// If the passwords are different -> return HttpStatus 400.
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok().build();
	}
	
	/*
	 * Handles logging in.
	 */
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginHandler(@RequestBody LoginCredentials body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
			
			// Attempts to authenticate the passed Authentication object
			authManager.authenticate(authInputToken);
			
			// Generates token.
			String token = jwtUtil.generateToken(body.getUsername()); 
			
			// Returns token inside the headers to the client.
			return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
					.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		} catch (org.springframework.security.core.AuthenticationException exc) {
			throw new RuntimeException("Invalid Login Credentials");
		}
	}

}
