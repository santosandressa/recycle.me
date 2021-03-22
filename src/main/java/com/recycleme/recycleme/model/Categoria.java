
package com.recycleme.recycleme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "categorias")
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
	 * Metais, plásticos, vidros, papéis, madeira
	 */
	@NotNull
	@Size(min = 6, max = 8)
	private String tipo;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String descarte;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescarte() {
		return descarte;
	}

	public void setDescarte(String descarte) {
		this.descarte = descarte;
	}
	
	
	
}
