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

	@Column(nullable = true)
	private String ent_nome;

	@Column(nullable = true)
	private String ent_idade;

	@Column(nullable = true)
	private String ent_n_guerra;

	@Column(nullable = true)
	private String ent_peso;

	@Column(nullable = true)
	private String ent_altura;

	/*
	 * 
	 * @Enumerated(EnumType.STRING) private TipoCadastro ent_tipo;
	 * 
	 * @Enumerated(EnumType.STRING) private TipoPessoa ent_tipopessoa;
	 * 
	 * 
	 * 
	 * @Column(nullable = true) private int ent_idade;
	 * 
	 * @Column(nullable = true) private String ent_genero;
	 * 
	 * @Column(nullable = true) private String ent_posto;
	 * 
	 * @Column(nullable = true) private String ent_grad;
	 * 
	 * 
	 * 
	 * @Column(nullable = true) private String ent_coren;
	 * 
	 * @Column(nullable = true) private String ent_profissao;
	 * 
	 * @IdentificaCampoPesquisa(descricaoCampo = "CRM", campoConsulta = "crm",
	 * principal = 1)
	 * 
	 * @Column(nullable = false, length = 25) private String ent_crm;
	 * 
	 * @Column(nullable = false, length = 20) private float ent_imc;
	 * 
	 * 
	 * @Version
	 * 
	 * @Column(name = "versionNum") private int versionNum;
	 */

	public int getVersionNum() {
		return versionNum;
	}

	public String getEnt_altura() {
		return ent_altura;
	}

	public void setEnt_altura(String ent_altura) {
		this.ent_altura = ent_altura;
	}

	public String getEnt_peso() {
		return ent_peso;
	}

	public void setEnt_peso(String ent_peso) {
		this.ent_peso = ent_peso;
	}

	public String getEnt_idade() {
		return ent_idade;
	}

	public void setEnt_idade(String ent_idade) {
		this.ent_idade = ent_idade;
	}

	public String getEnt_n_guerra() {
		return ent_n_guerra;
	}

	public void setEnt_n_guerra(String ent_n_guerra) {
		this.ent_n_guerra = ent_n_guerra;
	}

	public String getEnt_nome() {
		return ent_nome;
	}

	public void setEnt_nome(String ent_nome) {
		this.ent_nome = ent_nome;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Long getEnt_codigo() {
		return ent_codigo;
	}

	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
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

	public Date getEnt_datacadastro() {
		return ent_datacadastro;
	}

	public void setEnt_datacadastro(Date ent_datacadastro) {
		this.ent_datacadastro = ent_datacadastro;
	}

	public Date getEnt_ultimoacesso() {
		return ent_ultimoacesso;
	}

	public void setEnt_ultimoacesso(Date ent_ultimoacesso) {
		this.ent_ultimoacesso = ent_ultimoacesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ent_codigo == null) ? 0 : ent_codigo.hashCode());
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
		Entidade other = (Entidade) obj;
		if (ent_codigo == null) {
			if (other.ent_codigo != null)
				return false;
		} else if (!ent_codigo.equals(other.ent_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entidade [ent_codigo=" + ent_codigo + ", ent_login="
				+ ent_login + ", ent_senha=" + ent_senha + ", ent_mudarsenha="
				+ ent_mudarsenha + ", ent_inativo=" + ent_inativo
				+ ", ent_datacadastro=" + ent_datacadastro
				+ ", ent_ultimoacesso=" + ent_ultimoacesso + ", ent_nome="
				+ ent_nome + ", ent_idade=" + ent_idade + ", ent_n_guerra="
				+ ent_n_guerra + ", ent_peso=" + ent_peso + ", ent_altura="
				+ ent_altura + "]";
	}

}
