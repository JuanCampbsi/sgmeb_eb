package br.com.project.geral.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Produto;
import br.com.repository.interfaces.RepositoryProduto;
import br.com.srv.interfaces.SrvProduto;

@Controller
@RequestMapping(value= "/produto")
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
	

	
	@RequestMapping(value="grafico", method = RequestMethod.GET)
	public @ResponseBody String grafico(){
		
		String sql = "select count (1) as quantidade, prod_prazo as situacao"+
		"from produto"+
		"where cast(validade_prod as date) >= (current_date - 30)"+
		"group by prod_prazo";
		
		List<Object[]> lista = getSession().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		
		Object[] retorno = new Object[lista.size() + 1];
		int cont = 0;
		retorno[cont] = "[\"" + "Valido"+ "\"," + "\"" + "Quantidade " + "\"]";
		cont ++;
		
		for (Object[] object : lista) {
			retorno[cont] = "[\"" + object[1]+ "\"," + "\"" + object[0] + "\"]";
			cont ++;
		}
		
		
		return Arrays.toString(retorno);
	}
	
		
	
	

}