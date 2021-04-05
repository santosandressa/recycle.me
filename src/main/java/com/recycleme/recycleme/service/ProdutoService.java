package com.recycleme.recycleme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recycleme.recycleme.model.Categoria;
import com.recycleme.recycleme.model.Produto;
import com.recycleme.recycleme.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private @Autowired ProdutoRepository repositoryProduto;
	
	
	public List<Produto> pegarProdutoPorNome(String nome){
		return repositoryProduto.findByNomeContaining(nome);
	}
	
	public List<Produto> pegarPorCategoria(Categoria categoria){
		return repositoryProduto.findByCategoria(categoria);
	}

}
