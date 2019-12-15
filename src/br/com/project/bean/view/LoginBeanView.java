package br.com.project.bean.view;

import java.io.File;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.SessionControllerImpl;
import br.com.srv.interfaces.SrvLogin;

@Controller
@Scope(value = "request")
@ManagedBean(name = "loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	@Resource
	private SessionControllerImpl sessionController;

	@Autowired
	private SrvLogin srvLogin;
	
	@RequestMapping(value = "**/invalidar_session", method = RequestMethod.POST)
	public void invalidateSessionContrala(HttpServletRequest httpServletRequest) throws Exception {
		
		String userLogadoSessao = null;
		
		if (httpServletRequest.getUserPrincipal() != null){
			 userLogadoSessao = httpServletRequest.getUserPrincipal().getName();
		}

		if (userLogadoSessao == null || (userLogadoSessao != null && userLogadoSessao.trim().isEmpty())) {
			userLogadoSessao = httpServletRequest.getRemoteUser();
		}
		
		if (userLogadoSessao != null && !userLogadoSessao.isEmpty())
			sessionController.invalidateSession(userLogadoSessao);
	}

	@RequestMapping(value = "/publico/atualizarBanco", method = RequestMethod.GET)
	public void atualizarBanco(HttpServletResponse httpServletResponse,
			HttpServletRequest servletRequest) throws Exception {
		try {
			String pacote = "WEB-INF" + File.separator + "classes" + File.separator
					+ "sqlbaseDados";
	
			String caminhoFile = servletRequest.getServletContext().getRealPath(
					pacote);
	
			File file = new File(caminhoFile + File.separator);
	
			if (caminhoFile == null
					|| (caminhoFile != null && caminhoFile.isEmpty())
					|| file.list().length == 0) {
				caminhoFile = this.getClass()
						.getResource(File.separator + "sqlbaseDados").getPath();
			}
	
			file = null;
	
			srvLogin.atualizaBanco(caminhoFile);
			httpServletResponse.getWriter().write("{\"sucess\":\"ok\"}");
		}catch (Exception e) {
			httpServletResponse.getWriter().write("{\"sucess\":\"error\"}");
			throw new Exception("Erro ao atualizar base de dados" + e);
		}

	}

	@Override
	protected Class<?> getClassImplement() { 
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return null;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void invalidar(ActionEvent event) throws Exception {
		 RequestContext context = RequestContext.getCurrentInstance();
	        FacesMessage message = null;
	        boolean loggedIn = false;
	         
	        if(srvLogin.autentico(getUsername(), getPassword())) {
	        	sessionController.invalidateSession(getUsername());
	        	loggedIn = true;
	        } else {
	            loggedIn = false;
	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acesso negado, login ou senha incorretos.", "");
	        }
	        
	        if (message != null)
	        	FacesContext.getCurrentInstance().addMessage("msg", message);
	        
	        context.addCallbackParam("loggedIn", loggedIn);
	}

}
