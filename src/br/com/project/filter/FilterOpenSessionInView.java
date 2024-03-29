package br.com.project.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.filter.DelegatingFilterProxy;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.utils.UtilFramework;
import br.com.project.listener.ContextLoaderListenerGsmebUtils;
import br.com.project.model.classes.Pessoa;

/**
 * Responsavel por iniciar a sess�o/transaction do hibernate, iterceptar as
 * requisi��es/response, commit e rollback.
 * 
 * @author Juan Campos
 * 
 */
@SuppressWarnings("unused")
@WebFilter(filterName = "conexaoFilter")
public class FilterOpenSessionInView extends DelegatingFilterProxy implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private static SessionFactory sf;

	@Override
	public void initFilterBean() throws ServletException {
		sf = HibernateUtil.getSessionFactory();
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		BasicDataSource springDataSource = (BasicDataSource) ContextLoaderListenerGsmebUtils.getBean("springDataSource");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(springDataSource);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {

			servletRequest.setCharacterEncoding("UTF-8");

			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpSession sessao = request.getSession();
			Pessoa entLogadoSessao = (Pessoa) sessao.getAttribute("entLogadoSessao");

			if (entLogadoSessao != null) {
				UtilFramework.getThreadLocal().set(entLogadoSessao.getEnt_codigo());
			}

			sf.getCurrentSession().beginTransaction();
			chain.doFilter(servletRequest, servletResponse);

			transactionManager.commit(status);

			if (sf.getCurrentSession().getTransaction().isActive()) {
				sf.getCurrentSession().flush();
				sf.getCurrentSession().getTransaction().commit();
			}

			if (sf.getCurrentSession().isOpen()) {
				sf.getCurrentSession().close();
			}

			servletResponse.setCharacterEncoding("UTF-8");
			servletResponse.setContentType("text/html; charset=UTF-8");

		} catch (Exception e) {

			transactionManager.rollback(status);

			e.printStackTrace();

			if (sf.getCurrentSession().getTransaction().isActive()) {
				sf.getCurrentSession().getTransaction().rollback();
			}
			if (sf.getCurrentSession().isOpen()) {
				sf.getCurrentSession().close();
			}
		} finally {
			if (sf.getCurrentSession().isOpen()) {
				if (sf.getCurrentSession().beginTransaction().isActive()) {
					sf.getCurrentSession().flush();
					sf.getCurrentSession().clear();
				}
				if (sf.getCurrentSession().isOpen()) {
					sf.getCurrentSession().close();
				}
			}

		}
	}
}
