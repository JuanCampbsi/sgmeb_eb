package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Graduacao;
import br.com.repository.interfaces.RepositoryGraduacao;

@Repository
public class DaoGraduacao extends ImplementacaoCrud<Graduacao> implements
		RepositoryGraduacao {

	private static final long serialVersionUID = 1L;

}