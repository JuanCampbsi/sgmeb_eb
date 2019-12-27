package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.project.geral.controller.PacienteController;
import br.com.project.model.classes.Paciente;
import br.com.project.util.all.BeanViewAbstract;

@Controller
@Scope(value="session")
@ManagedBean(name = "pacienteBeanView")
public class PacienteBeanView extends BeanViewAbstract{

private static final long serialVersionUID = 1L;

	private Paciente objetoSelecionado = new Paciente();
	
	@Autowired
	private PacienteController pacienteController;
	
	/*public List<SelectItem> getPacientes() throws Exception {
		return pacienteController.getListPaciente();
	}*/
	
	@Override
		public String save() throws Exception {
			System.out.println(objetoSelecionado.getPes_nome());
			return "";
		}
	

	public Paciente getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Paciente objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	
	
}
