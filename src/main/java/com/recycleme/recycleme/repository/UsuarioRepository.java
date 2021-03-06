package com.recycleme.recycleme.repository;

import java.util.List;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recycleme.recycleme.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
	public List<Usuario>findAllByUsuarioContainingIgnoreCase(String usuario);
	public List<Usuario>findAllByCnpjContainingIgnoreCase(String cnpj);
	public Object findByEmail(String email);
	public Optional<Usuario> findByUsuario(String usuario);
}