package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryReceituario;
import br.com.srv.interfaces.SrvReceituario;

public class SrvReceituarioImpl implements SrvReceituario {

	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryReceituario repositoryReceituario;

	public void setRepositoryReceituario(
			RepositoryReceituario repositoryReceituario) {

		this.repositoryReceituario = repositoryReceituario;
	}

}
