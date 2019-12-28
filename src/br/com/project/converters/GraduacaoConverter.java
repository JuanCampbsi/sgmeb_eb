package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Graduacao;

@FacesConverter(forClass = Graduacao.class)
public class GraduacaoConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
				return (Graduacao) HibernateUtil.getCurrentSession().get(
						Graduacao.class, new Long(value));
		}
		return value;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Graduacao c = (Graduacao) value;
			return c.getGrad_codigo() != null && c.getGrad_codigo() > 0 ? c.getGrad_codigo().toString() : null;
		} else {
			return null;
		}
	}
}
