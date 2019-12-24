package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity(name = "medico")
@SequenceGenerator(name = "med_seq", sequenceName = "med_seq", initialValue = 1, allocationSize = 1)
public class Medico extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Médico", campoConsulta = "med_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_seq")
	private Long med_id;

	@IdentificaCampoPesquisa(descricaoCampo = "CRM", campoConsulta = "crm", principal = 1)
	@Column(nullable = false, length = 25)
	private String crm;

	@Audited
	@OneToMany(mappedBy = "medico", orphanRemoval = false)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private List<Consulta> reg_cons_list = new ArrayList<Consulta>();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getMed_id() {
		return med_id;
	}

	public void setMed_id(Long med_id) {
		this.med_id = med_id;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public List<Consulta> getReg_cons_list() {
		return reg_cons_list;
	}

	public void setReg_cons_list(List<Consulta> reg_cons_list) {
		this.reg_cons_list = reg_cons_list;
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
		result = prime * result + ((med_id == null) ? 0 : med_id.hashCode());
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
		Medico other = (Medico) obj;
		if (med_id == null) {
			if (other.med_id != null)
				return false;
		} else if (!med_id.equals(other.med_id))
			return false;
		return true;
	}

}
