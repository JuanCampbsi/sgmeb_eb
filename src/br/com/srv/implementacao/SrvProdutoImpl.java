package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryProduto;
import br.com.srv.interfaces.SrvProduto;

public class SrvProdutoImpl implements SrvProduto {

	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryProduto repositoryProduto;

	public void setRepositoryProduto(
			RepositoryProduto repositoryProduto) {

		this.repositoryProduto = repositoryProduto;
	}

}
