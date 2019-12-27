package br.com.framework.controller.crud;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.framework.implementacao.crud.ImplementacaoCrud;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
public class Controller extends ImplementacaoCrud<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public void persist(Object entidade) throws Exception {
		super.persist(entidade);
	}

	@Override
	public void saveOrUpdate(Object entidade) throws Exception {
		super.saveOrUpdate(entidade);
	}

	@Override
	public void delete(Object entidade) throws Exception {

		super.delete(entidade);
	}

	@Override
	public Object findById(Class entidade, Long id) throws Exception {

		return super.findById(entidade, id);
	}

	@Override
	public List findListByProperty(Class entidade, Object atributo, Object valor)
			throws Exception {
		return super.findListByProperty(entidade, atributo, valor);
	}

	@Override
	public List findListByListDeIds(Class entidade, List cods) throws Exception {
		return super.findListByListDeIds(entidade, cods);
	}

	@Override
	public List findListByQueryDinamica(String query) throws Exception {
		return super.findListByQueryDinamica(query);
	}

	@Override
	public List finList(Class entidade) throws Exception {

		return super.finList(entidade);
	}

	@Override
	public Object merge(Object entidade) throws Exception {

		return super.merge(entidade);
	}

	@Override
	public void update(Object entidade) throws Exception {
		super.update(entidade);
	}

	@Override
	public Class getClass(Class entidade) throws Exception {
		return super.getClass(entidade);
	}
}

