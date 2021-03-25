package com.recycleme.recycleme.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recycleme.recycleme.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
	public List<Avaliacao>findAllByAvaliacao(Long avaliacao);
	
}