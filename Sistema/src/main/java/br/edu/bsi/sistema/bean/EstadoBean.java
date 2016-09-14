package br.edu.bsi.sistema.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.edu.bsi.sistema.dao.EstadoDAO;
import br.edu.bsi.sistema.domain.Estado;
@SuppressWarnings("serial")
@ManagedBean
// A tela se comunica com a parte logica
@ViewScoped
// faz com que quando abre a tela entende que ela esta ligada com a visao que ja
// foi criada anteriormente
public class EstadoBean implements Serializable {
	private Estado estado;

	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro");
			erro.printStackTrace();

		}
	}

	public void novo() {
		estado = new Estado();
		// metodo que gera um novo objeto
	}
	@PostConstruct
	public void salvar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.salvar(estado);

			// implementação para atraves da list, e cada vez que for salvo sera
			// listado novamente os dados e serao mostrados na tela
			// dessa forma renovo a minha tabela de estados a cada exclusão ou
			// edição de dados.
			// instancia-se um novo para pode fazer os outros metodos, muda o
			// merge no genericDAO
			estado = new Estado();
			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado salvo com sucesso");
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
		estado = (Estado) evento.getComponent().getAttributes()
				.get("estadoSelecionado");
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.editar(estado);
	}

	// o metodo excluir funciona como o editar, a cada atualização a lista é
	// gerada novamente com os dados que ainda estao salvos
	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes()
					.get("estadoSelecionado");

			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);

			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}
	}
	
	/*public void imprimir() {
		try {
			
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			
			Map<String, Object> parametros = tabela.getFilters();

			
			//caminho de acesso ao relatório
			String caminho = Faces.getRealPath("/reports/estados.jasper");

			//cria a conexão
			Connection conexao = HibernateUtil.getConexao();

			//Criação do relatório
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			
			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}*/
	}