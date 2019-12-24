package br.com.srv.implementacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryUsuario;
import br.com.srv.interfaces.SrvUsuario;

@Service
public class SrvUsuarioImpl implements SrvUsuario {
	private static final long serialVersionUID = 1L;
	@Autowired
	private RepositoryUsuario repositoryUsuario;

	@Override
	public Date getUltimoAcessoUsuarioLogado(String name) {
		return repositoryUsuario.getUltimoAcessoUsuarioLogado(name);
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		repositoryUsuario.updateUltimoAcessoUser(login);
	}

	@Override
	public boolean existeUsuario(String user_login) {
		return repositoryUsuario.existeUsuario(user_login);
	}

}
