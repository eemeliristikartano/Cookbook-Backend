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
	static final String SECRET = System.getenv().get("MY_SECRET");
	// Expiration time for token
	static final long EXPIRATIONTIME = Long.parseLong(System.getenv().get("EXPIRATION_TIME"));
	
	/*
	 * Generates token.
	 */
	
	public String generateToken(String username) throws IllegalArgumentException, JWTCreationException {
		return JWT.create()
				.withSubject("User details")
				.withClaim("username", username)
				.withIssuedAt(new Date())
				.withIssuer("cookbook")
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME )) // Expiration time for token.
				.sign(Algorithm.HMAC256(SECRET)); // Creates a new JWT and signs it.
	}
	
	/*
	 * Validates token.
	 */
	
	public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
		// Verify JWT for it signature and claims.
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
				.withSubject("User details")
				.withIssuer("cookbook")
				.build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("username").asString();
	}

}
