package br.com.project.geral.controller;

import javax.annotation.Resource;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Consulta;
import br.com.repository.interfaces.RepositoryConsulta;
import br.com.srv.interfaces.SrvConsulta;

public class ConsultaController extends ImplementacaoCrud<Consulta> implements
		InterfaceCrud<Consulta> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvConsulta srvConsulta;
	@Resource
	private RepositoryConsulta repositoryConsulta;

	public void setSrvConsulta(SrvConsulta srvConsulta) {
		this.srvConsulta = srvConsulta;
	}

	public void setRepositoryConsulta(RepositoryConsulta repositoryConsulta) {
		this.repositoryConsulta = repositoryConsulta;
	}

}
