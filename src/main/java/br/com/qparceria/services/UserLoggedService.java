package br.com.qparceria.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.qparceria.security.UserSS;

public class UserLoggedService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		}
		catch(Exception e) {
			return null;
		}		
	}
}
