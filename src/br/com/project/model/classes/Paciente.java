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
@Entity(name="paciente")
@SequenceGenerator(name="paci_seq", sequenceName="paci_seq", initialValue=1, allocationSize=1)
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "paci_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paci_seq")
	private Long paci_id;
	
	@Column(nullable = false, length = 20)
	private float peso;
	
	@Column(nullable = false, length = 20)
	private float altura;
	
	@Column(nullable = false, length = 20)
	private float imc;
		

	@Audited 
	@OneToMany(mappedBy = "paciente", orphanRemoval = false)
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Consulta> consultlist = new ArrayList<Consulta>(); 
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getPaci_id() {
		return paci_id;
	}

	public void setPaci_id(Long paci_id) {
		this.paci_id = paci_id;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getImc() {
		return imc;
	}

	public void setImc(float imc) {
		this.imc = imc;
	}

	public List<Consulta> getConsultlist() {
		return consultlist;
	}

	public void setConsultlist(List<Consulta> consultlist) {
		this.consultlist = consultlist;
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
		result = prime * result + ((paci_id == null) ? 0 : paci_id.hashCode());
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
		Paciente other = (Paciente) obj;
		if (paci_id == null) {
			if (other.paci_id != null)
				return false;
		} else if (!paci_id.equals(other.paci_id))
			return false;
		return true;
	}
	
	
	
	
	

}
