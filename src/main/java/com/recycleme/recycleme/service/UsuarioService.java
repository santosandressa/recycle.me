package com.recycleme.recycleme.service;

import java.util.Optional;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.codec.binary.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.recycleme.recycleme.model.Avaliacao;
import com.recycleme.recycleme.model.Usuario;

import com.recycleme.recycleme.model.Produto;

import com.recycleme.recycleme.model.UsuarioLogin;
import com.recycleme.recycleme.repository.AvaliacaoRepository;
import com.recycleme.recycleme.repository.ProdutoRepository;
import com.recycleme.recycleme.repository.UsuarioRepository;
import com.recycleme.recycleme.util.CompraVenda;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositoryUsuario;

	@Autowired
	private ProdutoRepository repositoryProduto;

	@Autowired
	private AvaliacaoRepository repositoryAvaliacao;

	public Avaliacao postarAvaliacao(Avaliacao avaliacao, Long idUsuario) {
		Avaliacao novaAvaliacao = repositoryAvaliacao.save(avaliacao);
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);

		if (usuarioExistente.isPresent()) {
			novaAvaliacao.setUsuario(usuarioExistente.get());
			return repositoryAvaliacao.save(avaliacao);
		}
		return null;
	}
	
	//cadastrar usuario
	public Usuario cadastrarUsuario (Usuario novoUsuario) {
		Optional<Usuario> usuarioExistente = repositoryUsuario.findByUsuario(novoUsuario.getUsuario());
		if(usuarioExistente.isPresent()) {
			return null;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
		novoUsuario.setSenha(senhaCriptografada);
		return repositoryUsuario.save(novoUsuario);
	}
	
	//logar
	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> usuarioLogin){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioPresente = repositoryUsuario.findByUsuario(usuarioLogin.get().getUsuario());

		if(usuarioPresente.isPresent()) {
			if(encoder.matches(usuarioLogin.get().getSenha(), usuarioPresente.get().getSenha())) {
				String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				usuarioLogin.get().setToken(authHeader);				
				usuarioLogin.get().setNome(usuarioPresente.get().getNome());
				usuarioLogin.get().setSenha(usuarioPresente.get().getSenha());
				return usuarioLogin;
			}
		}
		return null;
	}
		
	//cadastrar produto
	public Produto cadastrarProduto(Produto novoProduto, Long idUsuario) {
		Produto produtoExistente = repositoryProduto.save(novoProduto);
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		
		if(usuarioExistente.isPresent()) {
			produtoExistente.setUsuario(usuarioExistente.get());
			return repositoryProduto.save(produtoExistente);
		}
		return null;
	}
	
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
