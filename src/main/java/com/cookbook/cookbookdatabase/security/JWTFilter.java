package com.cookbook.cookbookdatabase.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// Token from headers.
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
			// Substring to get only the token.
			String jwt = authHeader.substring(7);
			if (jwt == null || jwt.isBlank()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
			} else {
				try {
					// Validate token and get the user's username.
					String username = jwtUtil.validateTokenAndRetrieveSubject(jwt);
					// Get user's details. Password and role.
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
					if (SecurityContextHolder.getContext().getAuthentication() == null) {
						// Set authentication.
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				} catch (JWTVerificationException exc) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
					
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}
