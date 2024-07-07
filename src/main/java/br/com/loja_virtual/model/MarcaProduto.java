package br.com.loja_virtual.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "marca_produto") // Indicar o nome da tabela.
@SequenceGenerator(name = "seq_marca_produto", sequenceName = "seq_marca_produto", allocationSize = 1, initialValue = 1) // Define uma sequencia nas alterações das tabelas.
public class MarcaProduto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca_produto") // Define a estrategia do SequenceGenerator
	private Long id;
	
	@Column(nullable = false) // Esse parametro define que o campo descrição produto não pode ser vazio ou null, obrigatorio preencher.
	private String nomeDesc;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeDesc() {
		return nomeDesc;
	}
	public void setNomeDesc(String nomeDesc) {
		this.nomeDesc = nomeDesc;
	}

}
