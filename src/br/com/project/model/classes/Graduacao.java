package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "graduacao")
@SequenceGenerator(name = "grad_seq", sequenceName = "grad_seq", initialValue = 1, allocationSize = 1)
public class Graduacao implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grad_seq")
	private Long grad_codigo;	
	

	@Column(nullable = true)
	private  String grad_grad;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getGrad_codigo() {
		return grad_codigo;
	}

	public void setGrad_codigo(Long grad_codigo) {
		this.grad_codigo = grad_codigo;
	}

	

	public String getGrad_grad() {
		return grad_grad;
	}

	public void setGrad_grad(String grad_grad) {
		this.grad_grad = grad_grad;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((grad_codigo == null) ? 0 : grad_codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graduacao other = (Graduacao) obj;
		if (grad_codigo == null) {
			if (other.grad_codigo != null)
				return false;
		} else if (!grad_codigo.equals(other.grad_codigo))
			return false;
		return true;
	}
	
	
	
	
}
