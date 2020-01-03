package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfac.crud.InterfaceCrud;

@SuppressWarnings({ "unchecked" })
@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Autowired
	private JdbcTemplateImpl jdbcTemplate;

	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;

	@Autowired
	private SimpleJdbcInsertImplents simpleJdbcInsert;
	
	@Autowired
	protected SimpleJdbcClassImpl simpleJdbcClassImpl;


	// =========================================TEMPLATES DE OPERAÇÕES DE CRUD
	// EM JDBC
	// SPRING==============================================================
	public JdbcTemplateImpl getJdbcTemplate() {
		return jdbcTemplate;
	}

	public SimpleJdbcTemplateImpl getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public SimpleJdbcInsertImplents getSimpleJdbcInsert() {
		return simpleJdbcInsert;
	}
	
	public SimpleJdbcClassImpl getSimpleJdbcClassImpl() {
		return simpleJdbcClassImpl;
	}

	// =============================================================================================================================================

	/**
	 * Salva objeto no banco TestUnit ok
	 */
	@Override
	public void save(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	/**
	 * Salva objeto no banco TestUnit ok
	 */
	public void persist(T entidade) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().persist(entidade);
		executeFlushSession();
	}

	/**
	 * Salva ou atualiza objeto no banco TestUnit ok
	 */
	public void saveOrUpdate(Object entidade) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(entidade);
		executeFlushSession();
	}

	/**
	 * Atualiza objeto no banco TestUnit ok
	 */
	public void update(T entidade) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().update(entidade);
		executeFlushSession();
	}

	/**
	 * Deleta objeto do banco TestUnit ok
	 */
	public void delete(T entidade) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().delete(entidade);
		executeFlushSession();

	}

	/**
	 * Atualiza ou insere objeto no banco TestUnit ok
	 */
	public T merge(T entidade) throws Exception {
		validaSessionFactory();
		entidade = (T) sessionFactory.getCurrentSession().merge(entidade);
		executeFlushSession();
		return entidade;
	}

	/**
	 * Busca a lista de registro por classe informada TestUnit ok
	 */
	public List<T> finList(Class<T> entidade) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append("select distinct(entity) from ")
				.append(entidade.getSimpleName()).append(" entity ");
		List<T> lista = sessionFactory.getCurrentSession()
				.createQuery(query.toString()).list();
		return lista;
	}

	/**
	 * TestUnit ok
	 */
	public List<T> finListOrderByProperty(Class<T> entidade, String propriedade) {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append("select entity from ").append(entidade.getSimpleName())
				.append(" entity ").append(" order by entity.")
				.append(propriedade);
		List<T> lista = sessionFactory.getCurrentSession()
				.createQuery(query.toString()).list();
		return lista;
	}

	/**
	 * TestUnit Ok
	 */
	@Override
	public T findById(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		T ob = (T) sessionFactory.getCurrentSession().load(entidade, id);
		return ob;
	}

	public T findById(Class<T> entidade, String id) throws Exception {
		validaSessionFactory();
		T ob = (T) sessionFactory.getCurrentSession().load(entidade,
				Long.parseLong(id));
		return ob;
	}

	/**
	 * TestUnit Ok
	 */
	public List<T> findByPropertyId(Class<T> entidade, Long id, Object atributo)
			throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();

		query.append("select entity from ").append(entidade.getSimpleName())
				.append(" entity where entity.").append(atributo).append(" = ")
				.append(id);

		List<T> list = (List<T>) this.findListByQueryDinamica(query.toString());

		return list;

	}

	/**
	 * TestUnit Ok
	 */
	public T findUninqueByPropertyId(Class<T> entidade, Long id, Object atributo)
			throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();

		query.append("select entity from ").append(entidade.getSimpleName())
				.append(" entity where entity.").append(atributo).append(" = ")
				.append(id);

		T obj = (T) this.findUniqueByQueryDinamica(query.toString());

		return obj;

	}

	@Override
	public T findUninqueByPropertyId(Class<T> entidade, Long id,
			Object atributo, String condicaoAdicional) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();

		query.append("select entity from ").append(entidade.getSimpleName())
				.append(" entity where entity.").append(atributo).append(" = ")
				.append(id).append(" ").append(condicaoAdicional);

		T obj = (T) this.findUniqueByQueryDinamica(query.toString());

		return obj;

	}

	public T findUninqueByProperty(Class<T> entidade, Object valor,
			String atributo) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();

		query.append("select entity from ").append(entidade.getSimpleName())
				.append(" entity where entity.").append(atributo)
				.append(" = '").append(valor).append("'");

		T obj = (T) this.findUniqueByQueryDinamica(query.toString());

		return obj;

	}

	public T findUninqueByProperty(Class<T> entidade, Object valor,
			String atributo, String condicao) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();

		query.append("select entity from ").append(entidade.getSimpleName())
				.append(" entity where entity.").append(atributo)
				.append(" = '").append(valor).append("' ").append(condicao);

		T obj = (T) this.findUniqueByQueryDinamica(query.toString());

		return obj;

	}

	/**
	 * TestUnit Ok
	 */
	public List<T> findListByProperty(Class<T> entidade, Object atributo,
			Object valor) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select entity from ");
		query.append(entidade.getSimpleName());
		query.append(" entity where entity.");
		query.append(atributo);
		query.append(" = '");
		query.append(valor);
		query.append("' ");
		List<T> lista = sessionFactory.getCurrentSession()
				.createQuery(query.toString()).list();
		return lista;
	}

	/**
	 * TestUnit Ok
	 */
	public List<T> findListByListDeIds(Class<T> entidade, List<Long> cods)
			throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select entity from ");
		query.append(entidade.getSimpleName());
		query.append(" entity where entity.id in(");
		query.append(" :ids ");
		query.append(")");
		Query queryA = sessionFactory.getCurrentSession().createQuery(
				query.toString());
		queryA.setParameterList("ids", cods);
		return queryA.list();

	}

	/**
	 * TestUnit Ok
	 */
	public String getIds(List<Long> cods) throws Exception {
		String pks = "";
		int quantidade = cods.size();
		int cont = 0;
		for (Iterator<Long> iterator = cods.iterator(); iterator.hasNext();) {
			Long l = (Long) iterator.next();
			if (cont == quantidade - 1) {
				pks = (new StringBuilder(String.valueOf(pks))).append(
						l.toString()).toString();
			} else {
				pks = (new StringBuilder(String.valueOf(pks)))
						.append(l.toString()).append(", ").toString();
				cont++;
			}
		}
		return pks;

	}

	/**
	 * TestUnit Ok
	 */
	public List<T> findListByQueryDinamica(String query) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession()
				.createQuery(query.toString()).list();
		return lista;

	}

	/**
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir do
	 * registro passado no paramentro -> iniciaNoRegistro e obtendo maximo de
	 * resultados passados em -> maximoResultado.
	 * 
	 * @param query
	 * @param iniciaNoRegistro
	 * @param maximoResultado
	 * @return List<T>
	 * @throws Exception
	 */
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro,
			int maximoResultado) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession()
				.createQuery(query.toString()).setFirstResult(iniciaNoRegistro)
				.setMaxResults(maximoResultado).list();
		return lista;

	}

	/**
	 * TestUnit Ok
	 */
	public T findUniqueByQueryDinamica(String query) throws Exception {
		validaSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession()
				.createQuery(query.toString()).uniqueResult();
		return obj;
	}

	/**
	 * Retira objeto da sessao
	 */
	public void evict(Object entidade) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().evict(entidade);
	}

	/**
	 * Retorna sessão corrente
	 */
	@Override
	public Session getSession() {
		validaSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	/**
	 * TestUnit Ok
	 */
	@Override
	public T carregar(Class<T> classs, Long cod) throws Exception {
		validaSessionFactory();
		T t = (T) sessionFactory.getCurrentSession().get(classs, cod);
		return t;
	}

	/**
	 * TestUnit Ok
	 */
	public T saveRetorno(T entidade) throws Exception {
		validaSessionFactory();
		Object t = this.merge(entidade);
		executeFlushSession();
		sessionFactory.getCurrentSession().clear();
		return (T) t;
	}

	/**
	 * TestUnit Ok
	 */
	public void executeUpdateQueryDinamica(String query) throws Exception {
		validaSessionFactory();
		validaTransaction();

		sessionFactory.getCurrentSession().createQuery(query.toString())
				.executeUpdate();
		executeFlushSession();
	}

	/**
	 * TestUnit Ok
	 */
	public void executeUpdateSQLDinamica(String query, Long... cods)
			throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(query)
				.setParameterList("cods", cods).executeUpdate();
		executeFlushSession();
	}

	/**
	 * TestUnit Ok
	 */
	public void executeUpdateSQLDinamica(String query) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(query)
				.executeUpdate();
		executeFlushSession();
	}

	/**
	 * TestUnit Ok
	 */
	public List<Object[]> getListSQLDinamica(String sql) throws Exception {
		validaSessionFactory();
		List<Object[]> lista = (List<Object[]>) sessionFactory
				.getCurrentSession().createSQLQuery(sql).list();
		executeFlushSession();
		return lista;
	}

	@Override
	public Class<T> getClass(Class<T> entidade) throws Exception {
		return (Class<T>) entidade;
	}

	/**
	 * Roda instantaneamente o sql no banco de dados
	 * 
	 * @throws Exception
	 */
	private void executeFlushSession() throws Exception {
		sessionFactory.getCurrentSession().flush();
	}

	/**
	 * TestUnit Ok
	 */
	@Override
	public List<T> findListByLike(Class<T> entidade, String atributoClass,
			String valor) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select entity from ");
		query.append(entidade.getSimpleName());
		query.append(" entity ");
		query.append(" where upper(entity.");
		query.append(atributoClass);
		query.append(") like ");
		query.append("'%");
		query.append(valor.toUpperCase());
		query.append("%'");
		return this.findListByQueryDinamica(query.toString());
	}

	/**
	 * TestUnit Ok
	 */
	@Override
	public T findMaxObjectEntity(Class<T> classObj) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select entity from ");
		query.append(classObj.getSimpleName());
		query.append(" entity ");
		query.append(" where entity.id = ");
		query.append(" ( select max(id) from ");
		query.append(classObj.getSimpleName());
		query.append(" ) ");
		T t = (T) sessionFactory.getCurrentSession()
				.createQuery(query.toString()).uniqueResult();
		return t;
	}

	/**
	 * Limpa a sessão corrente
	 */
	@Override
	public void clearSession() {
		sessionFactory.getCurrentSession().clear();
	}

	/**
	 * Inicia a sessão caso não exista
	 */
	private void validaSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}

		validaTransaction();
	}

	private void validaTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive())
			sessionFactory.getCurrentSession().beginTransaction();
	}

	public void commitProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	public void rollbackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	/**
	 * Retorna o total de registro da tabela passada como parametro Return Long
	 */
	@Override
	public Long totalRegistro(String tabela) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from ").append(tabela);
		return jdbcTemplate.queryForLong(sql.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validaSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(
				query.toString());
		return queryReturn;
	}

}
