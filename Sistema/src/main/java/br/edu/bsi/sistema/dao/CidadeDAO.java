package br.edu.bsi.sistema.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.bsi.sistema.domain.Cidade;
import br.edu.bsi.sistema.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
	
	@SuppressWarnings("unchecked")
	public  List<Cidade> buscarPorEstado(Long estadoCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Cidade.class);
			//Restrictions é comparado ao where faz a comparanção dos icones
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
			
		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
				
				
	}

}
