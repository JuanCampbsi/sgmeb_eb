package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLogin  extends Serializable{

	void atualizaBanco(String sql, String fileName) throws Exception;

	boolean contemVersao(String fileSql) throws Exception;

	boolean contemTabela(String string);
	
	boolean autentico(String login, String senha)  throws Exception;

}