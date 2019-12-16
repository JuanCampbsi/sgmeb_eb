package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Index;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;


@Audited
@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 1, allocationSize = 1)
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "ent_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_seq")
	private Long ent_codigo;
	
	@Column(length = 20, nullable = true, unique = true)
	@Index(name = "xlogin")
	private String ent_login = null;

	@Column(length = 20, nullable = true)
	private String ent_senha;

	@Column(nullable = true)
	private Boolean ent_mudarsenha;

	@Column(nullable = false)
	private Boolean ent_inativo = false;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_datacadastro = new Date();
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimoacesso;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;

	protected int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public String getEnt_login() {
		return ent_login;
	}

	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}

	public String getEnt_senha() {
		return ent_senha;
	}

	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}

	public Long getEnt_codigo() {
		return ent_codigo;
	}

	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}
	
	public Boolean getEnt_mudarsenha() {
		return ent_mudarsenha;
	}

	public void setEnt_mudarsenha(Boolean ent_mudarsenha) {
		this.ent_mudarsenha = ent_mudarsenha;
	}
	
	public Boolean getEnt_inativo() {
		return ent_inativo;
	}

	public void setEnt_inativo(Boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}
	
	public Date getEnt_ultimoacesso() {
		return ent_ultimoacesso;
	}

	public void setEnt_ultimoacesso(Date ent_ultimoacesso) {
		this.ent_ultimoacesso = ent_ultimoacesso;
	}
	
	
}
