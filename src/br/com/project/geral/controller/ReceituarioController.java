package br.com.project.geral.controller;

import javax.annotation.Resource;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Receituario;
import br.com.repository.interfaces.RepositoryReceituario;
import br.com.srv.interfaces.SrvReceituario;

public class ReceituarioController extends ImplementacaoCrud<Receituario>
		implements InterfaceCrud<Receituario> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvReceituario srvReceituario;
	@Resource
	private RepositoryReceituario repositoryReceituario;

	public void setSrvReceituario(SrvReceituario srvReceituario) {
		this.srvReceituario = srvReceituario;
	}

	public void setRepositoryReceituario(
			RepositoryReceituario repositoryReceituario) {
		this.repositoryReceituario = repositoryReceituario;
	}

}