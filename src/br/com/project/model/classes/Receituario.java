package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity(name = "receituario")
@SequenceGenerator(name = "rec_seq", sequenceName = "rec_seq", initialValue = 1, allocationSize = 1)
public class Receituario implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Receituário", campoConsulta = "rec_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rec_seq")
	private Long rec_id;

	@Basic
	@OneToOne
	@JoinColumn(name = "receituario", nullable = false)
	@ForeignKey(name = "rec_paci_fk")
	private Consulta consulta = new Consulta();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getRec_id() {
		return rec_id;
	}

	public void setRec_id(Long rec_id) {
		this.rec_id = rec_id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
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
		result = prime * result + ((rec_id == null) ? 0 : rec_id.hashCode());
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
		Receituario other = (Receituario) obj;
		if (rec_id == null) {
			if (other.rec_id != null)
				return false;
		} else if (!rec_id.equals(other.rec_id))
			return false;
		return true;
	}

}
