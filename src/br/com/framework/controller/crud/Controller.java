package br.com.framework.controller.crud;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.framework.implementacao.crud.ImplementacaoCrud;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
public class Controller extends ImplementacaoCrud<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public void persist(Object usuario) throws Exception {
		super.persist(usuario);
	}

	@Override
	public void saveOrUpdate(Object usuario) throws Exception {
		super.saveOrUpdate(usuario);
	}

	@Override
	public void delete(Object usuario) throws Exception {

		super.delete(usuario);
	}

	@Override
	public Object findById(Class usuario, Long id) throws Exception {

		return super.findById(usuario, id);
	}

	@Override
	public List findListByProperty(Class usuario, Object atributo, Object valor)
			throws Exception {
		return super.findListByProperty(usuario, atributo, valor);
	}

	@Override
	public List findListByListDeIds(Class usuario, List cods) throws Exception {
		return super.findListByListDeIds(usuario, cods);
	}

	@Override
	public List findListByQueryDinamica(String query) throws Exception {
		return super.findListByQueryDinamica(query);
	}

	@Override
	public List finList(Class usuario) throws Exception {

		return super.finList(usuario);
	}

	@Override
	public Object merge(Object usuario) throws Exception {

		return super.merge(usuario);
	}

	@Override
	public void update(Object usuario) throws Exception {
		super.update(usuario);
	}

	@Override
	public Class getClass(Class usuario) throws Exception {
		return super.getClass(usuario);
	}
}

