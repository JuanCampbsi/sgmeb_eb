package br.com.project.geral.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.enums.TipoCadastro;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;
import br.com.srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements
		InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SrvEntidade srvEntidade;
	
	@Resource
	private RepositoryEntidade repositoryEntidade;

	public SrvEntidade getSrvEntidade() {
		return srvEntidade;
	}

	public void setSrvEntidade(SrvEntidade srvEntidade) {
		this.srvEntidade = srvEntidade;
	}

	public RepositoryEntidade getRepositoryEntidade() {
		return repositoryEntidade;
	}

	public void setRepositoryEntidade(RepositoryEntidade repositoryEntidade) {
		this.repositoryEntidade = repositoryEntidade;
	}

	public Entidade findUserLogado(String userLogado) throws Exception {
		return super.findUninqueByProperty(Entidade.class, userLogado,
				"ent_login", " and entity.ent_inativo is false ");
	}

	public Date getUltimoAcessoEntidadeLogada(String login) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	public void updateUltimoAcessoUser(String login) {
		srvEntidade.updateUltimoAcessoUser(login);
	}



	public boolean existeUsuario(String ent_login) {
		return srvEntidade.existeUsuario(ent_login);
	}
	
	
	
	public Entidade findPaciente(Long codEntidade) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codEntidade,
				"ent_codigo", " and entity.ent_inativo is false and entity.ent_nome = '"
						+ TipoCadastro.TIPO_CADASTRO_PACIENTE.name() + "'");
	}

	public boolean existeCpf(String ent_cpf) throws Exception {
		
		return super.findListByQueryDinamica("from Entidade where ent_cpf = '"+ ent_cpf + "'").size() > 0;
	
	}

	public boolean existeIdt(String ent_idtmilitar) throws Exception {
		return super.findListByQueryDinamica("from Entidade where ent_idtmilitar = '"+ ent_idtmilitar + "'").size() > 0;
		
	}

	
	

}

