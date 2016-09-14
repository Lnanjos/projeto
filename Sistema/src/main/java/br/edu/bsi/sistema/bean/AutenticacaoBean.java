package br.edu.bsi.sistema.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.edu.bsi.sistema.dao.UsuarioDAO;
import br.edu.bsi.sistema.domain.Pessoa;
import br.edu.bsi.sistema.domain.Usuario;

//managed permite manipular o Bean atraves da tela,
//a ligação da tela com o autenticacaoBean
@ManagedBean
// SessionScoped: cria uma sessao que dura enquanto a aplicação estiver aberta.
// quando se abre o software, ela cria uma sessao até que seja fechado o
// software
// ja o view so dura enquanto ambas as telas estiverem abertas, quando fecha ela
// desativa aoutra
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	// inicia o usuario e a pessoa atrelada
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			// usuarioLogado recebe o resultado do metodo que esta no usuario
			// DAO
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(),
					usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			}
			Faces.redirect("./pages/usuario.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
}
