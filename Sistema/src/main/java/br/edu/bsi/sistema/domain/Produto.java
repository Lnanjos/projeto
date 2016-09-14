package br.edu.bsi.sistema.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {

	@Column(length = 80, nullable = false)
	// vai ser uma coluna do banco
	private String descricao;

	@Column(nullable = false)
	private short quantidade;

	@Column(nullable = false, precision = 7, scale = 2)
	// precision numero de algarismos que um numero pode ter
	private BigDecimal preco;

	// cria uma relação de cardinalidade de muitos para muitos
	@ManyToOne
	// faz junção entre as colunas, como chave estrangeira e diz que não pode
	// ser nulo
	@JoinColumn(nullable = false)
	private Fabricante fabricante;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
