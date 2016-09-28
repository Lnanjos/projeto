package br.edu.bsi.sistema.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.bsi.sistema.dao.ClienteDAO;
import br.edu.bsi.sistema.dao.FuncionarioDAO;
import br.edu.bsi.sistema.dao.ProdutoDAO;
import br.edu.bsi.sistema.dao.VendaDAO;
import br.edu.bsi.sistema.domain.Cliente;
import br.edu.bsi.sistema.domain.Funcionario;
import br.edu.bsi.sistema.domain.ItemVenda;
import br.edu.bsi.sistema.domain.Produto;
import br.edu.bsi.sistema.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	private Venda venda = new Venda();
	// Preenche a datatable produtos
	private List<Produto> produtos;
	// Preenche a datatable "cesta de compras"
	private List<ItemVenda> itensVenda;
	private List<Funcionario> funcionarios;
	private List<Cliente> clientes;

	public Venda getVenda() {
		return venda;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listarOrdenado("descricao");

			// o array itensVenda deve ser inicializado junto com a tela
			// os itens selecionados serão enviados ao array.
			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erroao tentar carregar");
			erro.printStackTrace();

		}
	}

	public void finalizar() {
		try {
			venda.setHorario(new Date());
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios =  funcionarioDAO.listarOrdenado();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("");
			erro.printStackTrace();

		}
	}

	public void adicionar(ActionEvent evento) {

		// captura o objeto/linha e seus atributos
		Produto produto = (Produto) evento.getComponent().getAttributes()
				.get("produtoSelecionado");
		// variavel auxiliar que sera utilizada para verificar se o produto foi
		// encontrado
		int achou = -1;
		// laço de repetição que percorre o array itensVenda
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			// comparação do produto do array com o produto recebido pelo array
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}
		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);

		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1
					+ ""));
			itemVenda.setPrecoParcial(produto.getPreco().multiply(
					new BigDecimal(itemVenda.getQuantidade())));

		}
		calcular();
	}

	public void remover(ActionEvent evento) {
		// captura o objeto/linha e seus atributos
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes()
				.get("itemSelecionado");
		// variavel auxiliar que sera utilizada para verificar se o produto foi
		// encontrado
		int achou = -1;
		// laço de repetição que percorre o array itensVenda
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			// comparação do produto do array com o produto recebido pelo array
			if (itensVenda.get(posicao).getProduto()
					.equals(itemVenda.getProduto())) {
				achou = posicao;
			}
		}
		if (itemVenda.getQuantidade() > new Short("1")) {
			itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() - 1
					+ ""));
			itemVenda.setPrecoParcial(itemVenda.getProduto().getPreco()
					.multiply(new BigDecimal(itemVenda.getQuantidade())));

		} else {
			itensVenda.remove(achou);
		}
		calcular();

	}

	// para fazer o total
	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setPrecoTotal(venda.getPrecoTotal().add(
					itemVenda.getPrecoParcial()));
		}
	}
	
	public void salvar(){
		try {
			if (venda.getPrecoTotal().signum() == 0) {
				Messages.addGlobalError("Informe pelo menos um produto a ser comprado");
			}
			
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itensVenda);
			
			venda = new Venda();
			
			venda.setPrecoTotal(new BigDecimal("0.00"));
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listarOrdenado("descricao");
			
			itensVenda = new ArrayList<>();
			
			Messages.addGlobalInfo("Venda realizada com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			e.printStackTrace();
		}
	}
			
}
