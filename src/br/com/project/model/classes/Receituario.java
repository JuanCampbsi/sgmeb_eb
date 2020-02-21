package br.com.project.model.classes;

import java.io.Serializable;


public class Receituario implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Long rec_id;

	private Consulta consulta = new Consulta();
	
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
