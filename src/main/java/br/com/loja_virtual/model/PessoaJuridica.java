package br.com.loja_virtual.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
/*@PrimaryKeyJoinColumn - Devido a estrategia criada na classe pessoa, não é necessario adicionar essa anotação*/
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String cnpj;
	@Column(nullable = false)
	private String inscEstadual;
	private String inscMunicipal;
	private String nomeFantasia;
	@Column(nullable = false)
	private String razaoSocial;
	private String categoria;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscEstadual() {
		return inscEstadual;
	}
	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}
	public String getInscMunicipal() {
		return inscMunicipal;
	}
	public void setInscMunicipal(String inscMunicipal) {
		this.inscMunicipal = inscMunicipal;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
