package br.com.project.bean.view;


import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.ProdutoController;
import br.com.project.model.classes.Produto;


  
@ManagedBean(name="GraficoLinhaMB")
public class ProdutoGraficoController extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private Produto objetoSelecionado = new Produto();
	
	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private ProdutoController produtoController;
	
	private CarregamentoLazyListForObject<Produto> list = new CarregamentoLazyListForObject<Produto>();
	
	private PieChartModel model;
	public ProdutoGraficoController() {
	
	}
	public PieChartModel getModel() {
		objetoSelecionado = new Produto();		
	 	 model = new PieChartModel();
	 
	 	
	 	 model.set("30 dias fora!", 20);
	 	 model.set("60 dias fora!", 15);
		 	 return model;
	}
	@Override
	public void saveNotReturnAtual() throws Exception {
		// TODO Auto-generated method stub
		
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
     