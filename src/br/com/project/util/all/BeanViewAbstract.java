package br.com.project.util.all;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public abstract class BeanViewAbstract implements ActionViewPadrao {

	private static final long serialVersionUID = 1L;

	@Override
	public void limparLista() throws Exception {

	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {

	}

	@Override
	public void saveEdit() throws Exception {
	}

	@Override
	public void excluir() throws Exception {

	}

	@Override
	public String ativar() throws Exception {
		return null;
	}

	@Override
	public String novo() throws Exception {
		return null;
	}

	@Override
	public String editar() throws Exception {
		return null;
	}
	
	@Override
	public void selecao() throws Exception {
		
	}

	@Override
	public void setarVariaveisNulas() throws Exception {

	}

	@Override
	public void consultaEntidade() throws Exception {

	}

	@Override
	public String redirecionarNewEntidade() throws Exception {
		return null;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		return null;
	}
	
	protected void refresh() throws Exception {
     	
		 
		  /*Pega o contexto do JSF*/
		  ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		  
		  /*Pegas informações da solicitação e HTTP*/
		  StringBuffer requestURL = ((HttpServletRequest) ec.getRequest()).getRequestURL();
		  
		  /*Pega a URL completa da soliticação*/
		  String queryString = ((HttpServletRequest) ec.getRequest()).getQueryString();
		  
		  /*Permite mostrar as menssagens após redirecionamento*/
		  ec.getFlash().setKeepMessages(true);
		 
		  /*Faz o refresh da página JSF*/
		  ec.redirect((queryString == null) ? requestURL.toString() :
			  requestURL.append('?').append(queryString).toString());
  	}

	@Override
	public void statusOperation(EstatusPersistencia estatusPersistencia)
			throws Exception {
		Messagens.responseOperation(estatusPersistencia);
	}

	protected void sucesso() throws Exception {
		statusOperation(EstatusPersistencia.SUCESSO);
		refresh();
	}

	protected void error() throws Exception {
		statusOperation(EstatusPersistencia.ERROR);
	}
	
	@Override
	public void addMsg(String msg) {
		Messagens.msg(msg);
	}

}
