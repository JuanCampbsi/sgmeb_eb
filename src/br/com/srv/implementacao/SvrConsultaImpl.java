package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryConsulta;
import br.com.srv.interfaces.SrvConsulta;

public class SvrConsultaImpl implements SrvConsulta {

	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryConsulta repositoryConsulta;

	public void setRepositoryConsulta(RepositoryConsulta repositoryConsulta) {

		this.repositoryConsulta = repositoryConsulta;
	}
}
