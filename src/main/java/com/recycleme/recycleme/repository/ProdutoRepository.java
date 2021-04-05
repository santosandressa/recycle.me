package com.recycleme.recycleme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recycleme.recycleme.model.Produto;
import com.recycleme.recycleme.util.Categoria;
import com.recycleme.recycleme.util.CompraVenda;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	public List<Produto> findAllByCompraVenda(CompraVenda compraVenda);
	public List<Produto> findByCategoria(Categoria categoia);	 

}


