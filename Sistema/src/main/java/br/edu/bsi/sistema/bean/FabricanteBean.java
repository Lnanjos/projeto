package br.edu.bsi.sistema.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.edu.bsi.sistema.dao.FabricanteDAO;
import br.edu.bsi.sistema.domain.Fabricante;

@SuppressWarnings("serial")
// A tela se comunica com a parte logica
@ManagedBean
// faz com que quando abre a tela entende que ela esta ligada com a visao que ja
// foi criada anteriormente
@ViewScoped
public class FabricanteBean implements Serializable {

	private List<Fabricante> fabricantes;

	private Fabricante fabricante;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
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
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro");
			erro.printStackTrace();

		}
	}

	public void novo() {
		fabricante = new Fabricante();
		// metodo que gera um novo objeto
	}

	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricante);

			// implementação para atraves da list, e cada vez que for salvo sera
			// listado novamente os dados e serao mostrados na tela
			// dessa forma renovo a minha tabela de estados a cada exclusão ou
			// edição de dados.
			// instancia-se um novo para pode fazer os outros metodos, muda o
			// merge no genericDAO
			fabricante = new Fabricante();
			fabricantes = fabricanteDAO.listar();

			Messages.addGlobalInfo("Fabricante salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
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
		fabricante = (Fabricante) evento.getComponent().getAttributes()
				.get("fabricanteSelecionado");
	}

	// o metodo excluir funciona como o editar, a cada atualização a lista é
	// gerada novamente com os dados que ainda estao salvos
	public void excluir(ActionEvent evento) {
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes()
					.get("fabricanteSelecionado");

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricante);

			fabricantes = fabricanteDAO.listar();

			Messages.addGlobalInfo("Fabricante excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}
	}

}
