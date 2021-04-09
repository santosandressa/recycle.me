package com.recycleme.recycleme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recycleme.recycleme.model.Avaliacao;
import com.recycleme.recycleme.model.Usuario;
import com.recycleme.recycleme.repository.AvaliacaoRepository;
import com.recycleme.recycleme.repository.ProdutoRepository;
import com.recycleme.recycleme.repository.UsuarioRepository;

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
}