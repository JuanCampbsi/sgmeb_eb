package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Graduacao;

@Controller
public class GraduacaoController extends ImplementacaoCrud<Graduacao> implements
		InterfaceCrud<Graduacao> {
	private static final long serialVersionUID = 1L;

	public List<SelectItem> getListGraduacao() throws Exception{
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		List<Graduacao> graduacaos = super.findListByQueryDinamica("from Graduacao ORDER BY grad_codigo  ");
		
		for (Graduacao graduacao : graduacaos) {
			list.add(new SelectItem(graduacao, graduacao.getGrad_grad()));
		}
		return list;
		
	}
	
	
	


}
