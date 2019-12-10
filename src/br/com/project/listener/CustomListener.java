package br.com.project.listener;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import br.com.framework.utils.UtilFramework;
import br.com.project.model.classes.Usuario;
import br.com.project.model.classes.InformacaoRevisao;


@Service
public class CustomListener implements RevisionListener, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revisionEntity) {
			InformacaoRevisao informacaoRevisao = (InformacaoRevisao) revisionEntity;

			Long codUser = UtilFramework.getThreadLocal().get();
			Usuario user = new Usuario();
			if (codUser != null && codUser != 0L) {
				user.setEnt_codigo(codUser);
				informacaoRevisao.setEntidade(user);
			}
	}

}
