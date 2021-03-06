package br.edu.bsi.sistema.bean;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import java.util.Date;

import br.edu.bsi.sistema.dao.CaixaDAO;
import br.edu.bsi.sistema.dao.FuncionarioDAO;
import br.edu.bsi.sistema.domain.Caixa;
import br.edu.bsi.sistema.domain.Funcionario;

public class CaixaBean {
	private Caixa caixa;
	
	private ScheduleModel caixas;
	
	private List<Funcionario> funcionarios;

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public ScheduleModel getCaixas() {
		return caixas;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	@PostConstruct
	public void listar(){
		caixas=new DefaultScheduleModel();
	}
	
	public void novo(SelectEvent evento){
		//permite pegar a data do evento
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
		
	}	
	public void salvar(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(caixa.getDataAbertura());
		calendar.add(Calendar.DATE, 1);
		caixa.setDataAbertura(calendar.getTime());
		
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
		Messages.addGlobalInfo("Caixa aberto com sucesso");
	}
}
