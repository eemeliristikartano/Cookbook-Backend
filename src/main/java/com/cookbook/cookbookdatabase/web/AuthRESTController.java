package com.cookbook.cookbookdatabase.web;

import java.util.Collections;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookbook.cookbookdatabase.domain.LoginCredentials;
import com.cookbook.cookbookdatabase.domain.User;
import com.cookbook.cookbookdatabase.domain.UserRepository;
import com.cookbook.cookbookdatabase.security.JWTUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@CrossOrigin
public class AuthRESTController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping(value = "/register")
	public Map<String, Object> registerHandler(@RequestBody String newUserCredientals) {
		User newUser = new User();
		//System.err.println(user.getPassword());
		JsonObject credientals = JsonParser.parseString(newUserCredientals).getAsJsonObject();
		String password = credientals.get("password").getAsString();
		String encodedPassword = new BCryptPasswordEncoder().encode(password);
		newUser.setPassword(encodedPassword);
		newUser.setUsername(credientals.get("username").getAsString());
		newUser.setRole("USER");
		userRepo.save(newUser);
		String token = jwtUtil.generateToken(newUser.getUsername());
		return Collections.singletonMap("jwt-token", token);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginHandler(@RequestBody LoginCredentials body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
			
			authManager.authenticate(authInputToken);
			
			String token = jwtUtil.generateToken(body.getUsername()); // Generates token.
			
			return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
					.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		} catch (org.springframework.security.core.AuthenticationException exc) {
			throw new RuntimeException("Invalid Login Credentials");
		}
	}

}
