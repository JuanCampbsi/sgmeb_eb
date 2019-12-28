package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryGraduacao;
import br.com.srv.interfaces.SrvGraduacao;

@Service
public class SrvGraduacaoImpl implements SrvGraduacao {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryGraduacao repositoryGraduacao;

	public void setRepositoryBairro(RepositoryGraduacao repositoryGraduacao) {

		this.repositoryGraduacao = repositoryGraduacao;
	}
}