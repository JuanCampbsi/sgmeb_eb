package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Usuario;
import br.com.repository.interfaces.RepositoryUsuario;

@Repository
public class DaoUsuario extends ImplementacaoCrud<Usuario> implements
		RepositoryUsuario{

	private static final long serialVersionUID = 1L;

	@Override
	public Date getUltimoAcessoUsuarioLogado(String login) {
		SqlRowSet rowSet = getJdbcTemplate()
				.queryForRowSet(
						"select user_ultimoacesso from usuario where user_inativo is false and user_login = ?",
						new Object[] { login });
		return rowSet.next() ? rowSet.getDate("user_ultimoacesso") : null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		String sql = "update usuario  set user_ultimoacesso = current_timestamp where user_inativo is false and user_login = ? ";
		getSimpleJdbcTemplate().update(sql, login);
	}

	@Override
	public boolean existeUsuario(String user_login) {
		StringBuilder builder = new StringBuilder();
		builder.append(
				" select count(1) >= 1 from usuario where user_login = '")
				.append(user_login).append("' ");
		return super.getJdbcTemplate().queryForObject(builder.toString(),
				Boolean.class);
	}

}
