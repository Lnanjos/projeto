package br.edu.bsi.sistema.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.bsi.sistema.domain.ItemVenda;
import br.edu.bsi.sistema.domain.Venda;
import br.edu.bsi.sistema.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda>{

	// o método salvar recebe os objetos/entidades que serão persistidas
		public void salvar(Venda venda,List<ItemVenda> itensVenda){
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			Transaction transacao = null;
			try{
				transacao = sessao.beginTransaction();
				venda = (Venda) sessao.merge(venda);
				System.out.print("Esse é o codigo "+venda.getCodigo()+"\n");
				
				for(int posicao=0; posicao<itensVenda.size(); posicao++){
					ItemVenda itemVenda = itensVenda.get(posicao);
					
					itemVenda.setVenda(venda);
					
					sessao.save(itemVenda);
				}
				
				transacao.commit();
			}catch(RuntimeException erro){
				if (transacao!= null) {
					transacao.rollback();
				}
				throw erro;
			}finally{
				sessao.close();
			}
		}
		
}
