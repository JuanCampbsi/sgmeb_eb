package br.com.project.bean.view;

import java.util.HashSet;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.ProdutoController;
import br.com.project.model.classes.Produto;
import br.com.project.util.all.Messagens;

@Controller
@Scope(value = "session")
@ManagedBean(name = "produtoBeanView")
public class ProdutoBeanView extends BeanManagedViewAbstract {
	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Produto> list = new CarregamentoLazyListForObject<Produto>();
	private String url = "/cadastro/cad_produto.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_produto.jsf?faces-redirect=true";

	private Produto objetoSelecionado = new Produto();

	private HashSet<Long> idRemover = new HashSet<Long>();

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private ProdutoController produtoController;

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_produto");
		super.setNomeRelatorioSaida("report_produto");

		super.setListDataBeanColletionReport(produtoController
				.finList(getClassImplement()));
		return super.getArquivoReport();
	}

	public CarregamentoLazyListForObject<Produto> getList() throws Exception {
		return list;
	}

	public ProdutoController getProdutoController() {
		return produtoController;
	}

	public void setProdutoController(ProdutoController produtoController) {
		this.produtoController = produtoController;
	}

	@Override
	protected Class<Produto> getClassImplement() {
		return Produto.class;
	}

	@Override
	protected InterfaceCrud<Produto> getController() {
		return produtoController;
	}

	public Produto getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Produto objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	@RequestMapping(value = { "**/find_produto" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Produto();

	}

	@Override
	public void saveNotReturn() throws Exception {
		objetoSelecionado.isInvalido();
		objetoSelecionado.isInvalido60();
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			
			if(produtoController.existeSerie(objetoSelecionado.getSerie_prod())){
				
				Messagens.msgSeverityInfor("Este produto já existe cadastrado!");	
				
			}else {
			objetoSelecionado = produtoController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Produto();
			sucesso();
		}
		}
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getProd_codigo() != null
				&& objetoSelecionado.getProd_codigo() > 0) {
			produtoController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Produto();
			sucesso();
			list.clear();

		}

	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Produto();
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(),
				super.getSqlLazyQuery());
		
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}
	
	@Override
	public void saveNotReturnAtual() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado = produtoController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Produto();
			sucesso();
		}
		
	}
	

	@Override
	public void saveEdit() throws Exception {
		saveNotReturnAtual();
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

			Produto produto = produtoController.findById(getClassImplement(),id);
			produtoController.delete(produto);
			

		}
		sucesso();
		
		
		
		
	}

}
