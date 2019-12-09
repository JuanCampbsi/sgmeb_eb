package br.com.project.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

import br.com.framework.hibernate.session.HibernateUtil;
/**
 * Responsavel por manipular as execeções em JSF
 * @author Juan Campos
 *
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	// Obtém uma instância do FacesContext
	final FacesContext facesContext = FacesContext.getCurrentInstance();

	// Obtém um mapa do FacesContext
	final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();

	// Obtém o estado atual da navegação entre páginas do JSF
	final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

	// Declara o construtor que recebe uma exception do tipo ExceptionHandler
	// como parâmetro
	CustomExceptionHandler(ExceptionHandler exception) {
		this.wrapped = exception;
	}

	// Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	// Sobrescreve o método handle que é responsável por manipular as exceções
	// do JSF
	@Override
	public void handle() throws FacesException {

		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		
		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			// Recupera a exceção do contexto
			Throwable exception = context.getException();

			// Aqui tentamos tratar a exeção 
			try {

				requestMap.put("exceptionMessage", exception.getMessage());
				
				if (exception != null && exception.getMessage() != null && exception.getMessage().indexOf("ConstraintViolationException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_WARN,"Registro não pode ser removido por estar associado.",""));
				
				}
				else if (exception != null && exception.getMessage() != null && exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Registro foi atualizado ou excluído por outro usuário. Consulte novamente.",""));
					
				}
				else {
					// Avisa o usuário do erro
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_FATAL,"O sistema se recuperou de um erro inesperado.",""));

					// Tranquiliza o usuário para que ele continue usando o sistema
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_INFO,"Você pode continuar usando o sistema normalmente!",""));
					
					FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_FATAL,"O erro foi causado por:\n"+exception.getMessage(), ""));
					
					// SETA A NAVEGAÇÃO PARA UMA PÁGINA PADRÃO - REDIRECIONA PARA UMA PAGINA DE ERRO.
					//esse alert apenas é exibio se a pagina não redirecionar
					RequestContext.getCurrentInstance().execute("alert('O sistema se recuperou de um erro inesperado.')"); 
					
					 RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "O sistema se recuperou de um erro inesperado.."));
					
					navigationHandler.handleNavigation(facesContext, null, "/error/error.jsf?faces-redirect=true&expired=true");
				}

				// Renderiza a pagina de erro e exibe as mensagens
				facesContext.renderResponse();
			} finally {
				
				SessionFactory sf = HibernateUtil.getSessionFactory();
				
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				//imprimie exceção no console
				exception.printStackTrace();
				
				// Remove a exeção da fila
				iterator.remove();
			}
		}
		// Manipula o erro
		getWrapped().handle();
		
	}

}
