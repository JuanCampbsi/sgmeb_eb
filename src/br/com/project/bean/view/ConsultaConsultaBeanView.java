package br.com.project.bean.view;

import java.util.HashSet;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.ConsultaController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Consulta;

@Controller
@Scope("view")
@ManagedBean(name = "consultaConsultaBeanView")
public class ConsultaConsultaBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Consulta> list = new CarregamentoLazyListForObject<Consulta>();
	
	private HashSet<Long> idRemover = new HashSet<Long>();

	private Consulta objetoSelecionado = new Consulta();


	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private ConsultaController consultaController;

	@Autowired
	private EntidadeController entidadeController;

	public CarregamentoLazyListForObject<Consulta> getList() throws Exception {
		return list;
	}

	public ConsultaController getConsultaController() {
		return consultaController;
	}

	public void setConsultaController(ConsultaController consultaController) {
		this.consultaController = consultaController;
	}

	@Override
	protected Class<Consulta> getClassImplement() {
		return Consulta.class;
	}

	@Override
	protected InterfaceCrud<Consulta> getController() {
		return consultaController;
	}

	public Consulta getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Consulta objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}


	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}

	
	public void addRemover(javax.faces.event.AjaxBehaviorEvent behaviorEvent)
			throws Exception {

		boolean valorSelecionado = (boolean) ((SelectBooleanCheckbox) behaviorEvent
				.getSource()).getValue();

		String cons_id = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("cons_id");

		if (valorSelecionado) {
			idRemover.add(Long.parseLong(cons_id));
			
		} else {
			Iterator<Long> ids = idRemover.iterator();

			while (ids.hasNext()) {
				if (Long.parseLong(cons_id) == Long.parseLong(ids.next()
						.toString())) {
					ids.remove();
					break;
				
				}
			}
		}

	}

	
	public void removerMarcados() throws Exception {
		for (Long id : idRemover) {

			Consulta consulta = consultaController.findById(getClassImplement(),id);
			consultaController.delete(consulta);
			

		}
		sucesso();
	
	}

	@Override
	public void saveNotReturnAtual() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
