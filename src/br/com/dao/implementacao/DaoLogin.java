package br.com.dao.implementacao;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.repository.interfaces.RepositoryLogin;

public class DaoLogin extends ImplementacaoCrud<Object> implements
		RepositoryLogin {
	private static final long serialVersionUID = 1L;

	@Override
	public void atualizaBanco(String sql, String fileName) throws Exception {
		super.getJdbcTemplate().execute(sql);
		super.getJdbcTemplate().update(
				"insert into arquivoversao(arquivosql) values (?)",
				new Object[] { fileName });
	}

	@Override
	public boolean contemVersao(String fileSql) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select arquivosql from  arquivoversao  where arquivosql = '");
		sql.append(fileSql);
		sql.append("' limit 1");
		List<Object[]> temVersao = super.getListSQLDinamica(sql.toString());
		if (temVersao != null && !temVersao.isEmpty()) { 
			for (Object object : temVersao.toArray()) {
				return object.equals(fileSql);
			}
		}	
		return false;
	}

	@Override
	public boolean contemTabela(String tabela) {
		String sql = "SELECT table_name FROM information_schema.tables where table_name  = ?";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql,tabela);
		return sqlRowSet.next();

	}

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		String sql = "SELECT count(1) as autentica FROM entidade where ent_login  = ? and ent_senha = ? ";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql,new Object[]{login, senha});
		return sqlRowSet.next() ? sqlRowSet.getInt("autentica") > 0 : false;
	}

}