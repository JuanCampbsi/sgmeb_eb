package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.ConsultaController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Consulta;

@Controller
@Scope(value = "session")
@ManagedBean(name = "receituarioBeanView")
public class ReceituarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	
	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private ConsultaController consultaController;

	@Autowired
	private EntidadeController entidadeController;

	private Consulta objetoSelecionado = new Consulta();

	@Override
	public StreamedContent getArquivoReport() throws Exception {

		super.setNomeRelatorioJasper("report_receituario");
		super.setNomeRelatorioSaida("report_receituario");
		objetoSelecionado = new Consulta();

		Long cons_id = Long.parseLong(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("cons_id"));

		super.setListDataBeanColletionReport(consultaController.finListaUnica(
				Consulta.class, "cons_id", cons_id));
		return super.getArquivoReport();

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

}
