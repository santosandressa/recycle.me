package com.recycleme.recycleme.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recycleme.recycleme.util.CompraVenda;

@Entity
@Table(name = "produto")
public class Produto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private Double preco;
	
	@NotNull
	private Double quantidade;
	
	@NotNull
	private Double peso;
	
	@NotNull
	@Size(min = 5, max = 255)
	private String tipo_prod;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CompraVenda compraVenda;
	
	@ManyToOne
	@JsonIgnoreProperties({"categoria", "produto"})
	private Categoria categoria;
	
	@ManyToOne
	@JsonIgnoreProperties({"usuario", "produto"})
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getTipo_prod() {
		return tipo_prod;
	}

	public void setTipo_prod(String tipo_prod) {
		this.tipo_prod = tipo_prod;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public CompraVenda getCompraVenda() {
		return compraVenda;
	}

	public void setCompraVenda(CompraVenda compraVenda) {
		this.compraVenda = compraVenda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
