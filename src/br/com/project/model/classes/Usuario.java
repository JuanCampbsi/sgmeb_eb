package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import br.com.project.enums.TipoCadastro;


@Audited
@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
public class Usuario extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "user_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private Long user_codigo;

	@Enumerated(EnumType.STRING)
	private TipoCadastro user_tipo;	

	@Column(length = 20, nullable = true, unique = true)
	@Index(name = "xlogin")
	private String user_login = null;

	@Column(length = 20, nullable = true)
	private String user_senha;

	@Column(nullable = true)
	private Boolean user_mudarsenha;

	@Column(nullable = false)
	private Boolean user_inativo = false;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date user_datacadastro = new Date();

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date user_ultimoacesso;
	
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	

	public int getVersionNum() {
		return versionNum;
	}


	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}	


	public Long getUser_codigo() {
		return user_codigo;
	}


	public void setUser_codigo(Long user_codigo) {
		this.user_codigo = user_codigo;
	}


	public TipoCadastro getUser_tipo() {
		return user_tipo;
	}


	public void setUser_tipo(TipoCadastro user_tipo) {
		this.user_tipo = user_tipo;
	}


	public String getUser_login() {
		return user_login;
	}


	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}


	public String getUser_senha() {
		return user_senha;
	}


	public void setUser_senha(String user_senha) {
		this.user_senha = user_senha;
	}


	public Boolean getUser_mudarsenha() {
		return user_mudarsenha;
	}


	public void setUser_mudarsenha(Boolean user_mudarsenha) {
		this.user_mudarsenha = user_mudarsenha;
	}


	public Boolean getUser_inativo() {
		return user_inativo;
	}


	public void setUser_inativo(Boolean user_inativo) {
		this.user_inativo = user_inativo;
	}


	public Date getUser_datacadastro() {
		return user_datacadastro;
	}


	public void setUser_datacadastro(Date user_datacadastro) {
		this.user_datacadastro = user_datacadastro;
	}


	public Date getUser_ultimoacesso() {
		return user_ultimoacesso;
	}


	public void setUser_ultimoacesso(Date user_ultimoacesso) {
		this.user_ultimoacesso = user_ultimoacesso;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((user_codigo == null) ? 0 : user_codigo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (user_codigo == null) {
			if (other.user_codigo != null)
				return false;
		} else if (!user_codigo.equals(other.user_codigo))
			return false;
		return true;
	}

	
	
	
	
}
