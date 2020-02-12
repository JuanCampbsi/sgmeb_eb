package br.com.project.bean.view;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	private String url = "/cadastro/cad_paciente.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_paciente.jsf?faces-redirect=true";
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_paciente");
		super.setNomeRelatorioSaida("report_paciente");
		List<?> list = entidadeController.findListByProperty(Entidade.class, "ent_tipo", "TIPO_CADASTRO_PACIENTE");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}

	public void setEntidadeAtualizaSenhaBean(
			EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}


	private Entidade objetoSelecionado = new Entidade();

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private  EntidadeController entidadeController;



	public CarregamentoLazyListForObject<Entidade> getList() throws Exception {
		return list;
	}

	public String getEntidadeLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}

	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}

	public EntidadeController getEntidadeController() {
		return entidadeController;
	}

	public void setEntidadeController(EntidadeController entidadeController) {
		this.entidadeController = entidadeController;
	}

	@Override
	protected Class<Entidade> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}



	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	@RequestMapping(value = { "**/find_paciente" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Entidade();
		entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	}

	@Override
	public void saveNotReturn() throws Exception {
		objetoSelecionado.setEnt_tipo(TipoCadastro.TIPO_CADASTRO_PACIENTE);
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado = entidadeController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnt_codigo() != null
				&& objetoSelecionado.getEnt_codigo() > 0) {
			entidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Entidade();
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
		return urlFind;
	}

	
	
	@Override
	public String condicaoAndParaPesquisa() {
		return "and entity.ent_tipo = '"
				+ TipoCadastro.TIPO_CADASTRO_PACIENTE.name() + "' "
				+ consultarInativos();
	}
	
	
	public void updateSenha() throws Exception {
		Entidade entidadeLogada = contextoBean.getEntidadeLogada();
		if (!entidadeAtualizaSenhaBean.getSenhaAtual().equals(
				entidadeLogada.getEnt_senha())) {
			addMsg("A senha atual não é válida");
			return;
		} else if (entidadeAtualizaSenhaBean.getSenhaAtual().equals(
				entidadeAtualizaSenhaBean.getNovaSenha())) {
			addMsg("A senha atual não pode ser igual a nova senha.");
			return;
		} else if (!entidadeAtualizaSenhaBean.getNovaSenha().equals(
				entidadeAtualizaSenhaBean.getConfirmaSenha())) {
			addMsg("A nova senha e a confimação não conferem.");
			return;
		} else {
			entidadeLogada.setEnt_senha(entidadeAtualizaSenhaBean
					.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController.findById(Entidade.class,
					entidadeLogada.getEnt_codigo());
			if (entidadeLogada.getEnt_senha().equals(
					entidadeAtualizaSenhaBean.getNovaSenha())) {
				sucesso();
			} else {
				addMsg("A nova senha não pode ser atualizada.");
				error();
			}
		}

		entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	}

	
	@RequestMapping("**/findEntidade")
	public void findEntidade(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codEntidade") Long codEntidade)
			throws Exception {
		Entidade entidade = entidadeController.findPaciente(codEntidade);
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}

	}

}
