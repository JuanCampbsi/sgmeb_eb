package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private String url = "/cadastro/cad_entidade.jsf?faces-redirect=true";

	private Entidade objetoSelecionado = new Entidade();

	private List<Entidade> list = new ArrayList<Entidade>();

	public List<Entidade> getList() throws Exception {
		list = entidadeController.finList(Entidade.class);
		return list;
	}

	public void setList(List<Entidade> list) {
		this.list = list;
	}

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;

	public String getEntidadeLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}

	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "and entity.ent_tipo = '" + getTipoEntidadeTemp().name() + "' "
				+ consultarInativos();
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public void saveNotReturn() throws Exception { 
			if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado = entidadeController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new Entidade();
				sucesso();
			}
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
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}
	
	

	
}
