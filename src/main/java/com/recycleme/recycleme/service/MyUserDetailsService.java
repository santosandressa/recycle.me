package com.recycleme.recycleme.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recycleme.recycleme.repository.UsuarioRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	
	 @Autowired
	    private UsuarioRepository repository;
	 
	    public UserDetails loadUserByUsername(String email)
	      throws UsernameNotFoundException {
	 
	        Usuario usuario = repository.findByEmail(email);
	        if (usuario == null) {
	            throw new UsernameNotFoundException(
	              "No user found with username: "+ email);
	        }
	        boolean enabled = true;
	        boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;
	        return  new org.springframework.security.core.userdetails.Usuario
	          (usario.getEmail(), 
	          usuario.getPassword().toLowerCase(), enabled, accountNonExpired, 
	          credentialsNonExpired, accountNonLocked, 
	          getAuthorities(usuario.getRoles()));
	    }
	    
	    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        for (String role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role));
	        }
	        return authorities;
	    }
}
