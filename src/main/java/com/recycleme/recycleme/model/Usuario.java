package com.recycleme.recycleme.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_usuarios")
<<<<<<< HEAD
@ConfirmarSenha
=======
>>>>>>> 9d1ce37c7a84a24c5a8c6ca2b38aad8e2d71972d
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	@Size(min = 6, max = 60)
	private String username;

	@NotNull
	@Size(min = 5, max = 100)
	private String nome;

	@Size(min = 5, max = 255)
	@Column(unique = true)
	@Pattern(regexp = "/^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$/ ")
	private String cnpj;

	@NotNull
	@NotEmpty
	@Column(unique = true)
	@Size(min = 5, max = 45)
	@Valid
	private String email;

	@NotNull
	@Size(min = 5, max = 13)
	private String telefone;

	@NotNull
	@NotEmpty
	@Size(min = 6, max = 12)
	private String senha;
	private String confirmarSenha;
	
	@NotNull
	@Size(max = 10)
	private String cep;

	@NotNull
	@Size(max = 45)
	private String bairro;

	@NotNull
	@Size(max = 45)
	private String rua;

	@NotNull
	private String numero;

	@OneToMany(mappedBy= "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produto;
	
	@OneToMany(mappedBy= "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Avaliacao> avaliacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}