package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Produto;
import br.com.repository.interfaces.RepositoryProduto;


@Repository
public class DaoProduto extends ImplementacaoCrud<Produto> implements
		RepositoryProduto {

	private static final long serialVersionUID = 1L;
	
	
	

}