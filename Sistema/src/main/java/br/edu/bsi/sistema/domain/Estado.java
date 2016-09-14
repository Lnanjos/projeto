package br.edu.bsi.sistema.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
// dentro do entity pode-se alterar o nome da entidade do banco (name =)
@Entity
// vai criar a coluna no banco com o nome da minha classe
public class Estado extends GenericDomain {

	// Fazer anotaçoes, coloca a coluna onde sera colocado , length é o tamanho
	// e o nullable não pode ser nulo, pode definir o nome tambem
	@Column(length = 2, nullable = false)
	// Ele diz que o campo não é opcional e sim é obrigatorio...Vai até o banco
	// e verifica
	@Basic(optional = false)
	private String sigla;

	// Fazer anotaçoes, coloca a coluna onde sera colocado , length é o tamanho
	// e o nullable não pode ser nulo
	@Column(length = 50, nullable = false)
	// Ele diz que o campo não é opcional e sim é obrigatorio...Vai até o banco
	// e verifica
	@Basic(optional = false)
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
