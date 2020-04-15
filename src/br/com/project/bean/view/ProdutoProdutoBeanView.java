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
import br.com.project.geral.controller.ProdutoControllerGrafico;
import br.com.project.model.classes.Produto;

/*
 * 
 * Classe responsável por excluir objetos selecionados na tela pelo checkbox
 * 
 * */

@Controller
@Scope("view")
@ManagedBean(name = "produtoProdutoBeanView")
public class ProdutoProdutoBeanView extends BeanManagedViewAbstract {
	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Produto> list = new CarregamentoLazyListForObject<Produto>();

	private Produto objetoSelecionado = new Produto();

	private HashSet<Long> idRemover = new HashSet<Long>();

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private ProdutoControllerGrafico produtoControllerGrafico;

	public CarregamentoLazyListForObject<Produto> getList() throws Exception {
		return list;
	}

	public ProdutoControllerGrafico getProdutoController() {
		return produtoControllerGrafico;
	}

	public void setProdutoController(ProdutoControllerGrafico produtoControllerGrafico) {
		this.produtoControllerGrafico = produtoControllerGrafico;
	}

	@Override
	protected Class<Produto> getClassImplement() {
		return Produto.class;
	}

	@Override
	protected InterfaceCrud<Produto> getController() {
		return produtoControllerGrafico;
	}

	public Produto getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Produto objetoSelecionado) {
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

		String prod_codigo = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("prod_codigo");

		if (valorSelecionado) {
			idRemover.add(Long.parseLong(prod_codigo));
			
		} else {
			Iterator<Long> ids = idRemover.iterator();

			while (ids.hasNext()) {
				if (Long.parseLong(prod_codigo) == Long.parseLong(ids.next()
						.toString())) {
					ids.remove();
					break;
				
				}
			}
		}

	}

	
	public void removerMarcados() throws Exception {
		for (Long id : idRemover) {

			Produto produto = produtoControllerGrafico.findById(getClassImplement(),id);
			produtoControllerGrafico.delete(produto);
			

		}
		sucesso();
		
		
		
		
	}

	@Override
	public void saveNotReturnAtual() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
