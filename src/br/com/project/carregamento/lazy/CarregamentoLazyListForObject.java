package br.com.project.carregamento.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.framework.controller.crud.Controller;
import br.com.project.listener.ContextLoaderListenerGsmebUtils;

/**
 * Classe que implementa o carregamento preguiçoso (Carregamento por demanda)
 * para os dataTable do primefaces das telas Assim os carregamentos das tabelas
 * quando tiver muitos registros serão sempre rapidos e sem lentidão
 * 
 * @author Juan Campos
 * 
 * @param <T>
 */
public class CarregamentoLazyListForObject<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private List<T> list = new ArrayList<T>();

	private int totalRegistroConsulta = 0;

	private String query = null;

	private Controller controller = (Controller) ContextLoaderListenerGsmebUtils
			.getBean(Controller.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {

		try {
			if (query != null && !query.isEmpty())
				list = (List<T>) controller.findListByQueryDinamica(query,
						first, pageSize);

			if (totalRegistroConsulta == 0) {
				setRowCount(list.size());
			} else {
				setRowCount(totalRegistroConsulta);
			}
			setPageSize(pageSize);
		} catch (Exception e) {
		}

		return (List<T>) list;
	}

	public void setTotalRegistroConsulta(int totalRegistroConsulta,
			String queryDeBuscaDeConsulta) {
		this.query = queryDeBuscaDeConsulta;
		this.totalRegistroConsulta = totalRegistroConsulta;
	}

	public List<T> getList() {
		return list;
	}

	public void remove(T objetoSelecionado) {
		this.list.remove(objetoSelecionado);
	}

	public void clear() {
		this.query = null;
		this.totalRegistroConsulta = 0;
		this.list.clear();
	}

	public void add(T objetoSelecionado) {
		this.list.add(objetoSelecionado);
	}

	public void addAll(List<T> collections) {
		this.list.addAll(collections);
	}

	public Object getRowKey(T object) {
		return object;
	};

}
