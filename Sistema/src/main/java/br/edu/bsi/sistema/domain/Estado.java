package br.edu.bsi.sistema.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Estado extends GenericDomain {
	
	@Column(length = 2, nullable = false)
	@Basic(optional=false)
	private String sigla;

	@Column(length = 50, nullable = false)
	@Basic(optional=false)
	private String nome;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
