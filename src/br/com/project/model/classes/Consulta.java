package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity(name = "consulta")
@SequenceGenerator(name = "cons_seq", sequenceName = "cons_seq", initialValue = 1, allocationSize = 1)
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "cons_id" , principal = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cons_seq")
	private Long cons_id;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date cons_data = new Date();

	
	@Column(length = 250, nullable = false)
	private String sit_paci;

	
	@Column(length = 250, nullable = false)
	private String presc_paci;


	@Column(length = 100, nullable = false)
	private String hist_cons;


	@IdentificaCampoPesquisa(descricaoCampo = "Consulta", campoConsulta = "pessoa_codigo.ent_nome", principal = 3)
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "pessoa_codigo_fk")
	@JoinColumn(name = "pessoa_codigo", nullable = false)
	private Entidade pessoa_codigo = new Entidade();
	


	@Version
	@Column(name = "versionNum")
	private int versionNum;


	

	public Entidade getPessoa_codigo() {
		return pessoa_codigo;
	}

	public void setPessoa_codigo(Entidade pessoa_codigo) {
		this.pessoa_codigo = pessoa_codigo;
	}

	public Long getCons_id() {
		return cons_id;
	}

	public void setCons_id(Long cons_id) {
		this.cons_id = cons_id;
	}

	public Date getCons_data() {
		return cons_data;
	}

	public void setCons_data(Date cons_data) {
		this.cons_data = cons_data;
	}

	public String getSit_paci() {
		return sit_paci;
	}

	public void setSit_paci(String sit_paci) {
		this.sit_paci = sit_paci;
	}

	public String getPresc_paci() {
		return presc_paci;
	}

	public void setPresc_paci(String presc_paci) {
		this.presc_paci = presc_paci;
	}

	public String getHist_cons() {
		return hist_cons;
	}

	public void setHist_cons(String hist_cons) {
		this.hist_cons = hist_cons;
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
		result = prime * result + ((cons_id == null) ? 0 : cons_id.hashCode());
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
		Consulta other = (Consulta) obj;
		if (cons_id == null) {
			if (other.cons_id != null)
				return false;
		} else if (!cons_id.equals(other.cons_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Consulta [cons_id=" + cons_id + ", cons_data=" + cons_data
				+ ", sit_paci=" + sit_paci + ", presc_paci=" + presc_paci
				+ ", hist_cons=" + hist_cons + ", pessoa_codigo="
				+ "]";
	}


	

	

}
