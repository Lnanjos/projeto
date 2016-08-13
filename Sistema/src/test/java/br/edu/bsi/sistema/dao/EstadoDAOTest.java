package br.edu.bsi.sistema.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.bsi.sistema.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		//Lista que recebe os resultados do método listar
		//presente na classe GenericDAO
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		//foreach para percorrer a lista
		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	public void buscar(){
		Long codigo = 1L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		//acessar o método buscar e passar o parametro codigo
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	public void excluir(){
		Long codigo = 1L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			estadoDAO.excluir(estado);
			System.out.println("Registro removido:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		//11998652619
		}
	}
}
