package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Paciente;

@FacesConverter(forClass = Paciente.class)
public class PacienteConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
				return (Paciente) HibernateUtil.getCurrentSession().get(
						Paciente.class, new Long(codigo));
		}
		return codigo;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null) {
			Paciente c = (Paciente) objeto;
			return c.getPaci_id() != null && c.getPaci_id() > 0 ? c.getPaci_id().toString() : null;
		} 
			return null;
		
	}
}
