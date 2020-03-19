package br.com.project.geral.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Produto;
import br.com.repository.interfaces.RepositoryProduto;
import br.com.srv.interfaces.SrvProduto;

public class ProdutoController extends ImplementacaoCrud<Produto> implements
		InterfaceCrud<Produto> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvProduto srvProduto;
	@Resource
	private RepositoryProduto repositoryProduto;

	public void setSrvProduto(SrvProduto srvProduto) {
		this.srvProduto = srvProduto;
	}

	public void setRepositoryProduto(RepositoryProduto repositoryProduto) {
		this.repositoryProduto = repositoryProduto;
	}

	public boolean existeSerie(String serie_prod) throws Exception {
		return super.findListByQueryDinamica("from Produto where serie_prod = '"+ serie_prod + "'").size() > 0;
	}
	

	

	
	@RequestMapping(value="/gerarGraficoInicial", method = RequestMethod.GET)
	public @ResponseBody String gerarGraficoInicial( ){
		
		List<Map<String, Object>> totalConsultaGrafico = getTotalConsultaGrafico();
		
		int totalLinhas = totalConsultaGrafico.size() + 1;
		
		boolean semDados = false;
		if (totalLinhas <= 1){
			totalLinhas++;
			semDados = true;  
		}
		
		String[] dados = new String[totalLinhas];
		int cont = 0;
		
		dados[cont] = "[\"" + "30 dias na rota!"+"\"]";
		cont++;
		
		for (Map<String, Object> map : totalConsultaGrafico) {
			dados[cont] = "[\"" +  (String) map.get("situacao") +  "\"," + "\"" + 
		map.get("quantidade") +  "\"," +  "\"" +  map.get("prod_prazo") +  "\"]";
			cont++;
		}
		
		if (semDados){
			dados[cont] = "[\""  + 0  +    "\"]";
		}
		
		return Arrays.toString(dados);
	}

	
	private List<Map<String, Object>> getTotalConsultaGrafico() {
		StringBuilder sql = new StringBuilder();		
		sql.append("	select count (1) as quantidade, prod_prazo as situacao");

		sql.append("from produto");

		sql.append("where cast(validade_prod as date) >= (current_date - 30"); 
		sql.append("group by prod_prazo");
		return super.getSimpleJdbcTemplate().queryForList(sql.toString());
	}
	
	
	
	
	
	

}