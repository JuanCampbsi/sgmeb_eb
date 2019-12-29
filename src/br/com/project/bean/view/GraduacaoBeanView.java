package br.com.project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.GraduacaoController;
import br.com.project.model.classes.Graduacao;

@Controller
@Scope(value = "session")
@ManagedBean(name = "graduacaoBeanView")
public class GraduacaoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Graduacao objetoSelecionado = new Graduacao();

	@Autowired
	private GraduacaoController graduacaoController;

	public List<SelectItem> getGraduacaos() throws Exception {
		return graduacaoController.getListGraduacao();
	}

	public Graduacao getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Graduacao objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	protected Class<?> getClassImplement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
