package com.intiformation.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.intiformation.entity.Conseiller;
import com.intiformation.repository.IConseillerDao;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private IConseillerDao conseillerDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Conseiller conseiller = conseillerDao.findByEmail(username);
		
		if (conseiller == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(conseiller.getEmail(), conseiller.getMotDePasse(),
				new ArrayList<>());
	}


}