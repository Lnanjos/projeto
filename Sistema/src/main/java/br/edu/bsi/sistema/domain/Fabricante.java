package br.edu.bsi.sistema.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Fabricante extends GenericDomain {

	private String descricao;

	// Fazer anotaçoes, coloca a coluna onde sera colocado , length é o tamanho
	// e o nullable não pode ser nulo, pode definir o nome tambem
	@Column(length = 2, nullable = false)
	@Basic(optional = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
