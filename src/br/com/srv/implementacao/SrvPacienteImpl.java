package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryPaciente;
import br.com.srv.interfaces.SrvPaciente;

public class SrvPacienteImpl implements SrvPaciente {

	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryPaciente repositoryPaciente;

	public void setRepositoryPaciente(RepositoryPaciente repositoryPaciente) {

		this.repositoryPaciente = repositoryPaciente;
	}

}
