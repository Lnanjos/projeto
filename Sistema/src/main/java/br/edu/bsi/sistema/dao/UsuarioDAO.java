package br.edu.bsi.sistema.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.bsi.sistema.domain.Usuario;
import br.edu.bsi.sistema.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	// cpf e senha serao utilizados para a validação no login
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			// criar apelido para a pessoa par achamada dentro do metodo
			consulta.createAlias("pessoa", "p");

			// comparação de igual entre o cpf recebido pelo metodo
			// e o cpf presente no tabela/classe/objeto pessoa
			consulta.add(Restrictions.eq("p.cpf", cpf));

			// algoritmo de criptografia
			// utilização do md5
			// gera uma sequencia hexadecimal de 32 caracteres
			SimpleHash hash = new SimpleHash("md5", senha);

			// tohex() é o metodo utilizado para aplicar o metodo
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			//retorna apenas um resultado e faz um cast para usuario
			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
