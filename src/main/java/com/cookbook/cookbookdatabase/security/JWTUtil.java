package com.cookbook.cookbookdatabase.security;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;



@Component
public class JWTUtil {
	
	// Secret key
	static final String secret = System.getenv().get("MY_SECRET");
	
	/*
	 * Generates token.
	 */
	
	
	public String generateToken(String username) throws IllegalArgumentException, JWTCreationException {
		return JWT.create()
				.withSubject("User details")
				.withClaim("username", username)
				.withIssuedAt(new Date())
				.withIssuer("cookbook")
				.sign(Algorithm.HMAC256(secret));
	}
	
	public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
				.withSubject("User details")
				.withIssuer("cookbook")
				.build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("username").asString();
	}

}
