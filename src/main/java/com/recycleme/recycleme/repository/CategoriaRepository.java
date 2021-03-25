package com.recycleme.recycleme.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recycleme.recycleme.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	/*
	 * Retorna uma lista dos tipos de categoria
	 * @param tipo do tipo String
	 * @return Lista de tipo de Categoria se houver, caso contrário retorna lista vazia
	 * @Since 1.0
	 * @author Yuri Dias
	 */
	public List<Categoria> findAllByTipo (Enum<?> CategoriaTipo);
}
