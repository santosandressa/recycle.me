package com.recycleme.recycleme.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.util.privilegedactions.IsClassPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recycleme.recycleme.model.Produto;
import com.recycleme.recycleme.model.Usuario;
import com.recycleme.recycleme.repository.AvaliacaoRepository;
import com.recycleme.recycleme.repository.ProdutoRepository;
import com.recycleme.recycleme.repository.UsuarioRepository;
import com.recycleme.recycleme.util.CompraVenda;

@Service
public class UsuarioService{
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Autowired
	private ProdutoRepository repositoryProduto;
	
	@Autowired
	private AvaliacaoRepository avalicaoRepository;
	
	//cadastrar usuario
	//logar
	public Produto cadastrarProduto(Produto novoProduto, Long idUsuario) {
		Produto produtoExistente = repositoryProduto.save(novoProduto);
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		
		if(usuarioExistente.isPresent()) {
			produtoExistente.setUsuario(usuarioExistente.get());
			return repositoryProduto.save(produtoExistente);
		}
		return null;
	}
	
	//cadastrar avalicao

	//editar avaliacao

	//pegar usuario
	public List<Usuario>pegarUsuarioVendedor(CompraVenda compraVenda){
		return repositoryUsuario.findAllByCompraVenda(compraVenda);
	}
	//delete produto
	public Usuario deletarProduto(Long idProduto, Long idUsuario) {
		Optional<Produto> produtoExistente = repositoryProduto.findById(idProduto);
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		
		if(produtoExistente.isPresent() && usuarioExistente.isPresent()) {
			produtoExistente.get().setUsuario(null);
			repositoryProduto.save(produtoExistente.get());
			repositoryProduto.deleteById(produtoExistente.get().getId());
			return repositoryUsuario.findById(usuarioExistente.get().getId()).get();
		}
		return null;
	}
}
