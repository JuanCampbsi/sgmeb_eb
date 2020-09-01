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
import br.com.project.enums.TipoCadastro;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Pessoa;

/*
 * 
 * Classe responsável por excluir objetos selecionados na tela pelo checkbox
 * 
 * */

@Controller
@Scope("view")
@ManagedBean(name = "usuarioUsuarioBeanView")
public class UsuarioUsuarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Pessoa> list = new CarregamentoLazyListForObject<Pessoa>();

	private Pessoa objetoSelecionado = new Pessoa();
	
	private HashSet<Long> idRemover = new HashSet<Long>();
	

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;
	
	public CarregamentoLazyListForObject<Pessoa> getList() throws Exception {
		return list;
	}


	public EntidadeController getEntidadeController() {
		return entidadeController;
	}

	public void setEntidadeController(EntidadeController entidadeController) {
		this.entidadeController = entidadeController;
	}

	@Override
	protected Class<Pessoa> getClassImplement() {
		return Pessoa.class;
	}

	@Override
	protected InterfaceCrud<Pessoa> getController() {
		return entidadeController;
	}

	public Pessoa getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Pessoa objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	
	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "and entity.ent_tipo = '"
				+ TipoCadastro.TIPO_CADASTRO_USUARIO.name() + "' "
				+ consultarInativos();
	}
	
	public void addRemover(javax.faces.event.AjaxBehaviorEvent behaviorEvent) throws Exception {
	       
    	boolean valorSelecionado = (boolean ) ((SelectBooleanCheckbox)behaviorEvent.getSource()).getValue();
    	
    	String	ent_codigo = FacesContext.getCurrentInstance().getExternalContext().
    			getRequestParameterMap().get("ent_codigo");

    	if (valorSelecionado) {
	      idRemover.add(Long.parseLong(ent_codigo));
    	}else {
    		Iterator<Long> ids = idRemover.iterator();
    		
    		while (ids.hasNext()) {
    			if (Long.parseLong(ent_codigo) == Long.parseLong(ids.next().toString())) {
    				ids.remove();
    				break;
    			}
    		}
    	}
    	
    }
    
	  public void removerMarcados () throws Exception {
		  for (Long id : idRemover) {
			  
		     Pessoa pessoa = entidadeController.findById(getClassImplement(), id);
    	     entidadeController.delete(pessoa);
    	     
    	     
		}
		  sucesso();
		  redirecionarFindEntidade();
		  	
			
			
	  }


	@Override
	public void saveNotReturnAtual() throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}