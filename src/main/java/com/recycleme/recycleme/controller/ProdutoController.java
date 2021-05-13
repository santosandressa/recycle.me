package com.recycleme.recycleme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recycleme.recycleme.model.Produto;
import com.recycleme.recycleme.repository.ProdutoRepository;

import com.recycleme.recycleme.util.Categoria;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/recycleMe/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	ProdutoRepository repository;

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de produtos")
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um produto por ID")
	public ResponseEntity<Produto> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	@ApiOperation(value = "Retorna uma lista de produto que contenha o nome passado")
	public ResponseEntity<List<Produto>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findByNomeContaining(nome));
	}

	@GetMapping("/produtoCategoria/{categoria}")
	@ApiOperation(value = "Retorna uma lista de produtos baseado na categoria")
	public ResponseEntity<List<Produto>> GetByCategoira(@PathVariable Categoria categoria) {
		return ResponseEntity.ok(repository.findByCategoria(categoria));
	}

	@PutMapping
	@ApiOperation(value = "Edita um produto")
	public ResponseEntity<Produto> put(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um produto baseado no ID")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}