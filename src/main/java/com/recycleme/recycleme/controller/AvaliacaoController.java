package com.recycleme.recycleme.controller;

import java.util.List
;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.recycleme.recycleme.model.Avaliacao;
import com.recycleme.recycleme.repository.AvaliacaoRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/recycleMe/avaliacao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoRepository repository;

	@GetMapping
	@ApiOperation(value="Retorna uma lista de avaliações")
	public ResponseEntity<List<Avaliacao>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Retorna uma avaliação por ID")
	public ResponseEntity<Avaliacao> GetById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{avaliacao}")
	@ApiOperation(value="Retorna uma lista baseado em um número de 0 a 5")
	public ResponseEntity<List<Avaliacao>> GetByAvaliacao(@PathVariable Long avaliacao) {
		return ResponseEntity.ok(repository.findAllByAvaliacao(avaliacao));
	}

	@GetMapping("/classificacao")
	@ApiOperation(value="Retorna uma lista de avaliações maiores que 4")
	public ResponseEntity<List<Avaliacao>> GetByClassificacao() {
		return ResponseEntity.ok(repository.findAllBylCassificacao());
	}


	@DeleteMapping("/{id}")
	@ApiOperation(value="Deleta uma avaliação por ID")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}