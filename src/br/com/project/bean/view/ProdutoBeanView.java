package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

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



@Controller
@Scope(value = "session")
@ManagedBean(name = "produtoBeanView")
public class ProdutoBeanView extends BeanManagedViewAbstract {
	private static final long serialVersionUID = 1L;
	
	private CarregamentoLazyListForObject<Produto> list = new CarregamentoLazyListForObject<Produto>();
	private String url = "/cadastro/cad_produto.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_produto.jsf?faces-redirect=true";
	
	private Produto objetoSelecionado = new Produto();
	
	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private  ProdutoController produtoController;

	
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
		objetoSelecionado = new Produto();			
		valorPesquisa = "";
		list.clear();
		
		
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado = produtoController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Produto();
			sucesso();
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
		}
	}
	
	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Produto();
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
		objetoSelecionado = new Produto();		
		return urlFind;
	}
	
	

	
	
	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}



	

}
