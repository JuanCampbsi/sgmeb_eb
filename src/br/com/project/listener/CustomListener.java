package br.com.project.listener;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import br.com.framework.utils.UtilFramework;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.InformacaoRevisao;


@Service
public class CustomListener implements RevisionListener, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revisionEntity) {
			InformacaoRevisao informacaoRevisao = (InformacaoRevisao) revisionEntity;

			Long codEnt= UtilFramework.getThreadLocal().get();
			Entidade ent = new Entidade();
			if (codEnt != null && codEnt != 0L) {
				ent.setEnt_codigo(codEnt);
				informacaoRevisao.setPessoa(ent);
			}
	}

}