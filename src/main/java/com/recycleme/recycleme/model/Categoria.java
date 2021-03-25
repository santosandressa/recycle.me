
package com.recycleme.recycleme.model;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recycleme.recycleme.util.CategoriaTipo;
import com.sun.istack.NotNull;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/*
	 * Nome da categoria, exemplo:
	 * Aço, alumínio, baterias etc
	 * 
	 */
	
	@NotNull
	@Size(min = 3, max = 25)
	private String nome;

	/*
	 * Espera que a coluna tipo receba um das seguintes Strings:
	 * METAL,PLASTICO, VIDRO, PAPEL, MADEIRA
	 */
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoriaTipo tipo;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String descarte;
	
	@OneToMany(mappedBy="categoria", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public CategoriaTipo getTipo() {
		return tipo;
	}

	public void setTipo(CategoriaTipo tipo) {
		this.tipo = tipo;
	}

	public String getDescarte() {
		return descarte;
	}

	public void setDescarte(String descarte) {
		this.descarte = descarte;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
}
