package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.acessos.Permissao;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;
import br.com.project.util.all.Messagens;

@Controller
@Scope(value = "session")
@ManagedBean(name = "usuarioBeanView")
public class UsuarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();

	private String url = "/cadastro/cad_usuario.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_usuario.jsf?faces-redirect=true";
	private String urlPermissao = "/cadastro/cad_permissao.jsf?faces-redirect=true";
	private List<Permissao> listSelecionado = new ArrayList<Permissao>();
	private DualListModel<Permissao> listMenu = new DualListModel<Permissao>();

	private Entidade objetoSelecionado = new Entidade();
	
	private HashSet<Long> idRemover = new HashSet<Long>();
	

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_usuario");
		super.setNomeRelatorioSaida("report_usuario");
		List<?> list = entidadeController.findListByProperty(Entidade.class, "ent_tipo", "TIPO_CADASTRO_USUARIO");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	public DualListModel<Permissao> getListMenu() {
		permissao();
		setListMenu(new DualListModel<Permissao>(Permissao.getListPermissao(),
				listSelecionado));

		for (Permissao acesso : listSelecionado) {
			if (listMenu.getSource().contains(acesso)) {
				listMenu.getSource().remove(acesso);
			}
		}

		return listMenu;
	}

	public List<Permissao> getListSelecionado() {
		return listSelecionado;
	}

	public void setListSelecionado(List<Permissao> listSelecionado) {
		this.listSelecionado = listSelecionado;
	}

	public void setListMenu(DualListModel<Permissao> listMenu) {
		this.listMenu = listMenu;
	}

	public String permissao() {
		listSelecionado.clear();
		Iterator<Permissao> iterator = objetoSelecionado.getAcessosPermissao()
				.iterator();
		while (iterator.hasNext()) {
			listSelecionado.add(iterator.next());
		}

		Collections.sort(listSelecionado, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2
						.ordinal()));
			}
		});
		return urlPermissao;
	}

	public String savePermissoes() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado.getAcessos().clear();
			listSelecionado.clear();

			List<Permissao> permissoesConverter = getConvertPermissoes();

			for (Permissao permissao : permissoesConverter) {
				listSelecionado.add(permissao);
				objetoSelecionado.getAcessos().add(permissao.name());
			}
			objetoSelecionado = entidadeController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			sucesso();
		}
		return urlPermissao;
	}

	private List<Permissao> getConvertPermissoes() {
		List<Permissao> retorno = new ArrayList<Permissao>();
		Object[] acessos = (Object[]) listMenu.getTarget().toArray();

		for (Object object : acessos) {
			for (Permissao ace : Permissao.values()) {
				if (object.toString().equals(ace.name())) {
					retorno.add(ace);
				}
			}
		}
		return retorno;
	}

	public CarregamentoLazyListForObject<Entidade> getList() throws Exception {
		return list;
	}

	public String getEntidadeLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}

	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}

	public EntidadeController getEntidadeController() {
		return entidadeController;
	}

	public void setEntidadeController(EntidadeController entidadeController) {
		this.entidadeController = entidadeController;
	}

	@Override
	protected Class<Entidade> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	@RequestMapping(value = { "**/find_usuario" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Entidade();
	}

	@Override
	public void saveNotReturn() throws Exception {
		objetoSelecionado.setEnt_tipo(TipoCadastro.TIPO_CADASTRO_USUARIO);
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			if(entidadeController.existeUser(objetoSelecionado.getEnt_login())){
				
				Messagens.msgSeverityInfor("Este Login já existe cadastrado!");	
				
			}else {
			objetoSelecionado = entidadeController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
		}
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnt_codigo() != null
				&& objetoSelecionado.getEnt_codigo() > 0) {
			entidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Entidade();
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
			  
		     Entidade entidade = entidadeController.findById(getClassImplement(), id);
    	     entidadeController.delete(entidade);
    	     
    	     
		}
		  sucesso();
		  redirecionarFindEntidade();
		  	
			
			
	  }

	@Override
	public void saveNotReturnAtual() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado = entidadeController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
		
	}
	

}