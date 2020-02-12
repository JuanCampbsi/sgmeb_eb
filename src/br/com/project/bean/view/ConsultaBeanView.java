package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.ConsultaController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Consulta;
import br.com.project.model.classes.Entidade;

@Controller
@Scope(value="session")
@ManagedBean(name = "consultaBeanView")
public class ConsultaBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	
	private CarregamentoLazyListForObject<Consulta> list = new CarregamentoLazyListForObject<Consulta>();
	private String url = "/cadastro/cad_consulta.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_consulta.jsf?faces-redirect=true";
	
	private Consulta objetoSelecionado = new Consulta();
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_consulta");
		super.setNomeRelatorioSaida("report_consulta");

		super.setListDataBeanColletionReport(consultaController
				.finList(getClassImplement()));
		return super.getArquivoReport();
	}
	

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
	@RequestMapping(value = { "**/find_consulta" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {					
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Consulta();		
		
		
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado = consultaController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Consulta();
			sucesso();
		}
	}
	
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getCons_id() != null
				&& objetoSelecionado.getCons_id() > 0) {
			consultaController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Consulta();
			sucesso();
		}
	}
	
	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Consulta();
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
		
		
		
}
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();		
		objetoSelecionado = new Consulta();		
		return urlFind;
	}
	
	

	
	
	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}


	
	
	
	
	
	
	  @RequestMapping("**/addPacienteFunc")
	public void addPacienteFunc(@RequestParam Long codEntidade) throws Exception  {
		if (codEntidade != null && codEntidade > 0) {
				Entidade entidade = entidadeController
						.findPaciente(codEntidade);
				objetoSelecionado.setEntidade(entidade != null ? entidade
						: new Entidade());
		}}
	
	
	

}

	
	
