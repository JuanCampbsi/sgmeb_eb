package br.com.project.geral.controller;

import javax.annotation.Resource;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Paciente;
import br.com.repository.interfaces.RepositoryPaciente;
import br.com.srv.interfaces.SrvPaciente;

public class PacienteController extends ImplementacaoCrud<Paciente> implements
		InterfaceCrud<Paciente> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvPaciente srvPaciente;
	@Resource
	private RepositoryPaciente repositoryPaciente;

	public void setSrvPaciente(SrvPaciente srvPaciente) {
		this.srvPaciente = srvPaciente;
	}

	public void setRepositoryPaciente(RepositoryPaciente repositoryPaciente) {
		this.repositoryPaciente = repositoryPaciente;
	}

}
