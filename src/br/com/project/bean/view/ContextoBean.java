package br.com.project.bean.view;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.SessionController;
import br.com.project.model.classes.Pessoa;

@Scope(value = "session")
@Component(value = "contextoBean")
public class ContextoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String USER_LOGADO_SESSAO = "userLogadoSessao";
	
	@Autowired
	private EntidadeController entidadeController;
	
	@Resource
	private SessionController sessionController;

	/**
	 * 
	 * @return String Usuário logado remote user
	 */
	public String getUserLogado() {
		return getExternalContext().getRemoteUser();
	}

	/**
	 * 
	 * @return String Usuário logado user principal
	 */
	public String getUserPrincipal() {
		return getExternalContext().getUserPrincipal().getName();
	}

	/**
	 * 
	 * @return String Usuário logado Authentication Spring security
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 
	 * @return ExternalContext da application
	 */
	public ExternalContext getExternalContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		return external;
	}

	/**
	 * @return Entidade logado na sessão atualmente
	 */
	public Pessoa getEntidadeLogada() throws Exception {
		Pessoa pessoa = (Pessoa) getExternalContext().getSessionMap().get(USER_LOGADO_SESSAO);
		
		if (pessoa == null || (pessoa != null && !pessoa.getEnt_login().equals(getUserPrincipal()))) {
			if (getAuthentication().isAuthenticated()) {
				entidadeController.updateUltimoAcessoUser(getAuthentication().getName());
				pessoa = entidadeController.findUserLogado(getAuthentication().getName());
				getExternalContext().getSessionMap().put(USER_LOGADO_SESSAO, pessoa);
				sessionController.addSession(pessoa.getEnt_login(), (HttpSession) getExternalContext().getSession(false));
			}
		}
		return pessoa;
	}
	
	/**
	 * Retorna se o usuario logado possui os acesso passados como paramentro, este metodo destina-se ao uso onde não é possivel usar
	 * as tags do spring security
	 */
	public boolean possuiAcesso(String... acessos) {
		for (String acesso : acessos) {
			for (GrantedAuthority autorizacao : getAuthentication().getAuthorities()){ 
				if (autorizacao.getAuthority().trim().equals(acesso.trim())){
					return true;
				}
			}
		}
		return false;
	}

}
