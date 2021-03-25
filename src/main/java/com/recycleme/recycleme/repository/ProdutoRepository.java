package com.recycleme.recycleme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recycleme.recycleme.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	public List<Produto> findAllByCompraVendaContaining(Enum<?> compravenda);

}


