package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryMedico;
import br.com.srv.interfaces.SrvMedico;

public class SrvMedicoImpl implements SrvMedico {

	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryMedico repositoryMedico;

	public void setRepositoryMedico(RepositoryMedico repositoryMedico) {

		this.repositoryMedico = repositoryMedico;
	}

}
