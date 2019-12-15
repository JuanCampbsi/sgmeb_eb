package br.com.srv.implementacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryLogin;
import br.com.srv.interfaces.SrvLogin;

public class SrvLoginImpl implements SrvLogin {
	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryLogin repositoryLogin;
	
	private BufferedReader bufferedReader;

	public void setRepositoryLogin(RepositoryLogin repositoryLogin) {

		this.repositoryLogin = repositoryLogin;
	}

	@Override
	public void atualizaBanco(String path) throws Exception {
		path += File.separator;
		File file = new File(path);
		String sqlText = "";
		String[] arquivos = file.list();
		Arrays.sort(arquivos);
		for (String fileSql : arquivos) {
			sqlText = "";

			if (fileSql.contains(".sql") && fileSql.equals("v_1.sql")) {
				sqlText = obterConteudo(path, fileSql);
				if (!sqlText.trim().isEmpty() && !repositoryLogin.contemTabela("arquivoversao")) {
					repositoryLogin.atualizaBanco(sqlText, fileSql);
				}
			} else if (fileSql != null && fileSql.contains(".sql")
					&& !repositoryLogin.contemVersao(fileSql)) {
				sqlText = obterConteudo(path, fileSql);

				if (!sqlText.trim().isEmpty())
					repositoryLogin.atualizaBanco(sqlText, fileSql);
			}

		}
	}

	private String obterConteudo(String path, String fileSql)
			throws FileNotFoundException, IOException {
		String sqlText = "";
		bufferedReader = new BufferedReader(new FileReader(path
				+ fileSql));
		if (bufferedReader != null) {
			while (bufferedReader.ready()) {
				String linha = bufferedReader.readLine();
				if (linha != null && !linha.trim().isEmpty()) {
					sqlText += linha;
				}
			}
		}
		return sqlText;
	}

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		return repositoryLogin.autentico(login, senha);
	}
}