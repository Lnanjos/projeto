package br.edu.bsi.sistema.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.edu.bsi.sistema.domain.Funcionario;
import br.edu.bsi.sistema.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO <Funcionario> {
	
	//lista ordenada
		@SuppressWarnings("unchecked")
		public List<Funcionario> listarOrdenado(){
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				// é um componente do hibernate para listagem e para consultar
				Criteria consulta = sessao.createCriteria(Funcionario.class);
				//critério da ordenação o asc significa que vai do maior para o menor, e coloca o parametro
				//(pessoa , p) é a classe que possui o atributo relacionado com o apelido
				consulta.createAlias("pessoa", "p");
				consulta.addOrder(Order.asc("p.nome"));
				List<Funcionario> resultado = consulta.list();
				return resultado;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
		

}
