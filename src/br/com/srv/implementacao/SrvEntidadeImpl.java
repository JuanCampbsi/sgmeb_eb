package br.com.srv.implementacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryEntidade;
import br.com.srv.interfaces.SrvEntidade;

@Service
public class SrvEntidadeImpl implements SrvEntidade {
	private static final long serialVersionUID = 1L;
	@Autowired
	private RepositoryEntidade repositoryEntidade;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		return repositoryEntidade.getUltimoAcessoEntidadeLogada(name);
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		repositoryEntidade.updateUltimoAcessoUser(login);
	}

	@Override
	public boolean existeUsuario(String ent_login) {
		return repositoryEntidade.existeUsuario(ent_login);
	}

}
