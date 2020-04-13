package br.com.project.bean.view;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;


  
@ManagedBean(name="GraficoLinhaMB")
public class ProdutoGraficoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PieChartModel model;
	public ProdutoGraficoController() {
		 	 model = new PieChartModel();
		 	 model.set("Brand 1", 540);
		 	 model.set("Brand 2", 325);
		 	 model.set("Brand 3", 702);
		 	 model.set("Brand 4", 421);
	}
	public PieChartModel getModel() {
		 	 return model;
	}

	
	
}
     