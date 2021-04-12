package com.recycleme.recycleme.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.recycleme.recycleme.model.Avaliacao;

import com.recycleme.recycleme.model.Produto;

import com.recycleme.recycleme.model.Usuario;
import com.recycleme.recycleme.model.UsuarioLogin;
import com.recycleme.recycleme.repository.AvaliacaoRepository;
import com.recycleme.recycleme.repository.UsuarioRepository;
import com.recycleme.recycleme.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/recycleMe/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AvaliacaoRepository repositoryAvaliacao;;

	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<List<Usuario>> GetByCnpj(@PathVariable String cnpj) {
		return ResponseEntity.ok(repository.findAllByCnpjContainingIgnoreCase(cnpj));
	}

	@GetMapping("/nomeUsuario/{usuario}")
	public ResponseEntity<List<Usuario>> getByUsername(@PathVariable String usuario) {
		return ResponseEntity.ok(repository.findAllByUsuarioContainingIgnoreCase(usuario));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastro(@Valid @RequestBody Usuario novoUsuario) {
		return new ResponseEntity<Usuario>(usuarioService.cadastrarUsuario(novoUsuario), HttpStatus.CREATED);
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> auth(@RequestBody Optional<UsuarioLogin> usuarioLogin) {
		return usuarioService.logar(usuarioLogin).map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping
	public ResponseEntity<Usuario> PostUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}

	@PutMapping
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}

	// acoes do usuario

	@PostMapping("/avaliacao/nova/{id_usuario}")
	public ResponseEntity<?> postarAvaliacao(
			@PathVariable(value = "id_usuario") Long idUsuario,
			@Valid @RequestBody Avaliacao avaliacao) {
		Avaliacao cadastro = usuarioService.postarAvaliacao(avaliacao, idUsuario);
		if(cadastro==null) {
			return new ResponseEntity<String>("Falha no cadastro da avaliacao", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Avaliacao>(cadastro, HttpStatus.CREATED);
	}

	@PutMapping("/avaliacao/{avaliacaoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {

		Optional<Avaliacao> avaliacaoAtual = repositoryAvaliacao.findById(id);

		if (avaliacaoAtual != null) {
			avaliacaoAtual = repositoryAvaliacao.save(avaliacaoAtual);
			return ResponseEntity.ok(avaliacaoAtual);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PostMapping("/produto/novo/{id_usuario}")
	public ResponseEntity<?> cadastrarProduto(
			@PathVariable(value = "id_usuario") Long idUsuario,
			@Valid @RequestBody Produto novoProduto){
		Produto cadastro = usuarioService.cadastrarProduto(novoProduto, idUsuario);
	
		if(cadastro==null) {
			return new ResponseEntity<String>("Falha no cadastro", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Produto>(cadastro, HttpStatus.CREATED);
	}

	@DeleteMapping("/produto/delete/{id_Produto}/{id_Usuario}")
	public ResponseEntity<?> removerProduto(
			@PathVariable(value = "id_Produto")Long idProduto,
			@PathVariable(value = "id_Usuario")Long idUsuario){
		Usuario retorno = usuarioService.deletarProduto(idProduto, idUsuario);
		if(retorno == null) {
			return new ResponseEntity<String>("Produto ou Usuario Invalido",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(retorno, HttpStatus.ACCEPTED);
	}
}