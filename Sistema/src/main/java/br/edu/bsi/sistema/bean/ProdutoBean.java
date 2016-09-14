package br.edu.bsi.sistema.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.bsi.sistema.dao.FabricanteDAO;
import br.edu.bsi.sistema.dao.ProdutoDAO;
import br.edu.bsi.sistema.domain.Fabricante;
import br.edu.bsi.sistema.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
// A tela se comunica com a parte logica
@ViewScoped
// faz com que quando abre a tela entende que ela esta ligada com a visao que ja
// foi criada anteriormente
public class ProdutoBean implements Serializable{

	private Produto produto;
	private String descricao;
	private short quantidade;
	private double preco;

	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro");
			erro.printStackTrace();

		}
	}

	public void novo() {
		produto = new Produto();
		// metodo que gera um novo objeto
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produto);

			// implementação para atraves da list, e cada vez que for salvo sera
			// listado novamente os dados e serao mostrados na tela
			// dessa forma renovo a minha tabela de estados a cada exclusão ou
			// edição de dados.
			// instancia-se um novo para pode fazer os outros metodos, muda o
			// merge no genericDAO
			produto = new Produto();
			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}

	// criar o método editar, usando o actionevent que gera um evento na pagina,
	// vai editar um estado,
	// meu getcomponent é a linha adicionada pela caneta, ai pega os atributos
	// do componente, e o estado selecionado
	// pega o elemento atraves do get e usa coo atributo.para isso modifica o
	// metodo salvar
	public void editar(ActionEvent evento) {
		
		try {
			produto = (Produto) evento.getComponent().getAttributes()
					.get("produtoSelecionado");

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();

		}
	}

	// o metodo excluir funciona como o editar, a cada atualização a lista é
	// gerada novamente com os dados que ainda estao salvos
	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes()
					.get("produtoSelecionado");

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);

			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}

}
