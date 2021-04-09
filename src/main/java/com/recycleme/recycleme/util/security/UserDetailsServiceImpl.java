package com.recycleme.recycleme.util.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recycleme.recycleme.model.Usuario;
import com.recycleme.recycleme.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private @Autowired UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByUsuario(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + "not found"));
		
		return usuario.map(UserDetailsImpl::new).get();
	}
}
