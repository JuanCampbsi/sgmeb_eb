package br.com.project.geral.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
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
@Scope(value = "request")
@ManagedBean(name = "produtografico")
public class ProdutoControllerGrafico extends ImplementacaoCrud<Produto> implements
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
	

		
	@RequestMapping(value="**/grafico", method = RequestMethod.GET)
	public @ResponseBody String grafico(){
	
	List<Map<String, Object>> 	lista = getValidadeGrafico();
		
		
		int retorno = lista.size() + 1;
		
		String[] dados = new String[retorno];
		int cont = 0;
		
		boolean semDados = false;
		if(semDados){
			dados[cont ++] = "[\"" + "Sem Dados"+ "\"," + "\"" + "Total" + "\"]";
		}else{
			dados[cont] = "[\"" + "Estado" + "\"," + "\"" + "Total ." + "\"]";
			cont ++;
			
			for (Map<String, Object>  objeto : lista) {
				dados[cont] = "[\"" + objeto.get("situacao")+ "\"," + "\"" + objeto.get("quantidade") + "\"]";
				cont ++;
			}
			
		}
		
		
		return Arrays.toString(dados);
	}

	private List<Map<String, Object>> getValidadeGrafico(){
	StringBuilder sql = new StringBuilder();
	
	sql.append("select count (1) as quantidade, prod_prazo as situacao ");
	sql.append("from produto where cast(validade_prod as date) >= (current_date - 30) ");
	sql.append("group by prod_prazo order by quantidade asc ");	

	return super.getJdbcTemplate().queryForList(sql.toString());
	
	}
	
	

}