package br.edu.bsi.sistema.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.edu.bsi.sistema.domain.Usuario;
import br.edu.bsi.sistema.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	//cpf e senha serão utilizados para a validação no login
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			//criação do apelido da classe Pessoa para chamada dentro do método
			consulta.createAlias("pessoa", "p");

			//comparação de igualdade entre o CPF recebido pelo método 
			// e o cpf presente na tabela/classe/objeto pessoa
			consulta.add(Restrictions.eq("p.cpf", cpf));

			//algortimo de criptografia
			//utilização do algortimo md5
			//gera um sequência hexadecimal de 32 caracteres
			SimpleHash hash = new SimpleHash("md5", senha);
			//toHex() é o método utilizado para aplicar o algortimo md5 na senha
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			//retorna apenas um resultado e faz um "cast" para usuário
			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
