package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Consulta;
import br.com.repository.interfaces.RepositoryConsulta;

@Repository
public class DaoConsulta extends ImplementacaoCrud<Consulta> implements
		RepositoryConsulta {

	private static final long serialVersionUID = 1L;

}
