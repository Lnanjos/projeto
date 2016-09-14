package br.edu.bsi.sistema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//dentro do entity pode-se alterar o nome da entidade do banco (name =)
@SuppressWarnings("serial")
//vai criar a coluna no banco com o nome da minha classe
@Entity
public class Cidade extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String nome;

	// cria uma relação de cardinalidade de muitos para muitos

	// faz junção entre as colunas, como chave estrangeira
	@ManyToOne
	@JoinColumn(nullable = false)
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
