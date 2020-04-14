package br.com.project.bean.view;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.model.classes.Produto;


  
@ManagedBean(name="GraficoLinhaMB")
public class ProdutoGraficoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto objetoSelecionado = new Produto();
	
	private CarregamentoLazyListForObject<Produto> list = new CarregamentoLazyListForObject<Produto>();
	
	private PieChartModel model;
	public ProdutoGraficoController() {
	
	}
	public PieChartModel getModel() {
		objetoSelecionado = new Produto();		
	 	 model = new PieChartModel();
	 	 model.set("Válido 1", objetoSelecionado.getTotal());
	 	 model.set("30 dias fora! 2", 20);
	 	 model.set("60 dias fora! 3", 15);
		 	 return model;
	}

	
	
}
     