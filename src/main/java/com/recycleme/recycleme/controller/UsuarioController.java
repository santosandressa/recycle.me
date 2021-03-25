package com.recycleme.recycleme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recycleme.recycleme.model.Usuario;
import com.recycleme.recycleme.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/v1/recycleMe/usuario")
public class UsuarioController {
	
	@Autowired 
	private UsuarioRepository repository;
	
	@GetMapping("/username/{username}")
	public ResponseEntity<List<Usuario>> getByUsername(@PathVariable String username)
	{
		return ResponseEntity.ok(repository.findAllByUsernameContainingIgnoreCase(username));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) 
	{
		repository.deleteById(id);
	}
}