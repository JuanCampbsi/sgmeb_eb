package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;

@Repository
public class DaoEntidade extends ImplementacaoCrud<Entidade> implements
		RepositoryEntidade {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String login) {
		SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(
				"select ent_ultimoacesso from entidade where ent_inativo is false and ent_login = ?",
				new Object[] { login });
		return rowSet.next() ? rowSet.getDate("ent_ultimoacesso") : null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		String sql = "update entidade  set ent_ultimoacesso = current_timestamp where ent_inativo is false and ent_login = ? ";
		getSimpleJdbcTemplate().update(sql, login);
	}

	@Override
	public boolean existeUsuario(String ent_login) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select count(1) >= 1 from entidade where ent_login = '").append(ent_login).append("' ");
		return super.getJdbcTemplate().queryForObject(builder.toString(), Boolean.class);
	}

}
