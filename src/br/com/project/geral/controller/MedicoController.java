package br.com.project.geral.controller;

import javax.annotation.Resource;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Medico;
import br.com.repository.interfaces.RepositoryMedico;
import br.com.srv.interfaces.SrvMedico;

public class MedicoController extends ImplementacaoCrud<Medico> implements
		InterfaceCrud<Medico> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvMedico srvMedico;
	@Resource
	private RepositoryMedico repositoryMedico;

	public void setSrvMedico(SrvMedico srvMedico) {
		this.srvMedico = srvMedico;
	}

	public void setRepositoryMedico(RepositoryMedico repositoryMedico) {
		this.repositoryMedico = repositoryMedico;
	}

}
