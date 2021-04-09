package com.recycleme.recycleme.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recycleme.recycleme.model.Produto;

import com.recycleme.recycleme.util.Categoria;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	public List<Produto>findByNomeContaining(String nome);
	public List<Produto>findByCategoria(Categoria categoria);
}


