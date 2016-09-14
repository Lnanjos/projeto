package br.edu.bsi.sistema.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import br.edu.bsi.sistema.domain.Cliente;
import br.edu.bsi.sistema.util.HibernateUtil;

public class ClienteDAO extends GenericDAO <Cliente>{

	//lista ordenada
			@SuppressWarnings("unchecked")
			public List<Cliente> listarOrdenado(){
				Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
				try {
					// é um componente do hibernate para listagem e para consultar
					Criteria consulta = sessao.createCriteria(Cliente.class);
					//critério da ordenação o asc significa que vai do maior para o menor, e coloca o parametro
					//(pessoa , p) é a classe que possui o atributo relacionado com o apelido
					consulta.createAlias("pessoa", "p");
					consulta.addOrder(Order.asc("p.nome"));
					List<Cliente> resultado = consulta.list();
					return resultado;
				} catch (RuntimeException erro) {
					throw erro;
				} finally {
					sessao.close();
				}
			}

}
