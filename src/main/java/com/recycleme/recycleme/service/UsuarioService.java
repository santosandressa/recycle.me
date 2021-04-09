package com.recycleme.recycleme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recycleme.recycleme.model.Produto;
import com.recycleme.recycleme.repository.AvaliacaoRepository;
import com.recycleme.recycleme.repository.ProdutoRepository;
import com.recycleme.recycleme.repository.UsuarioRepository;

@Service
public class UsuarioService{
	
	@Autowired
	private UsuarioRepository reporitoryUsuario;
	
	@Autowired
	private ProdutoRepository repositoryProduto;
	
	@Autowired
	private AvaliacaoRepository avalicaoRepository;
	
	
	
	//cadastrar usuario
	//logar

	//cadastrar produto

	public Produto cadastrarProduto(Produto novoProduto, Long idUsuario) {
		Produto produtoExistente = repositoryProduto.save(novoProduto);
	}
	
	//comprar

	//cadastrar avalicao

	//editar avaliacao

	//pegar usuario (vendendor e comprador)
	//delete produto
	
}
