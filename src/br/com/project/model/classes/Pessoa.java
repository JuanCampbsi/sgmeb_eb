package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.acessos.Permissao;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.enums.TipoCadastro;

@SuppressWarnings("deprecation")
@Audited
@Entity
@Table(name = "pessoa")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 1, allocationSize = 1)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	
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

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "ent_nome")
	@Column(nullable = true)
	private String ent_nome;

	@Column(nullable = true)
	private String ent_idade;	

	@Column(length = 100, nullable = true)
	private String ent_endereco;	

	@Column(unique = true, nullable = true)
	private String ent_cpf;	
	
	@Column(length = 15, nullable = true)
	private String ent_celular;
	
	@Column(length = 9, nullable = true)
	private String ent_cep;

	@Column(unique = true,nullable = true )
	private String ent_idtmilitar;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome de Guerra", campoConsulta = "ent_n_guerra")
	@Column(nullable = true)
	private String ent_n_guerra;

	@Column(nullable = true)
	private String ent_peso;
	
	@Column(nullable = true)
	private String texto_ativo;

	@Column(nullable = true)
	private String ent_altura;

	@Enumerated(EnumType.STRING)
	private TipoCadastro ent_tipo;

	@IdentificaCampoPesquisa(descricaoCampo = "C�digo", campoConsulta = "graduacao.grad_codigo", principal = 1)
	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "graduacao")
	@ForeignKey(name = "grad_codigo_fk")
	private Graduacao graduacao = new Graduacao();
	
	
	@CollectionOfElements
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "entidadeacesso", uniqueConstraints = { @UniqueConstraint(name = "unique_acesso_entidade_key", columnNames = {
			"ent_codigo", "esa_codigo" }) }, joinColumns = { @JoinColumn(name = "ent_codigo") })
	@Column(name = "esa_codigo", length = 20)
	private Set<String> acessos = new HashSet<String>();

	public Graduacao getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(Graduacao graduacao) {
		this.graduacao = graduacao;
	}
	
	
	public Set<Permissao> getAcessosPermissao() {
		Set<Permissao> permissoes = new HashSet<Permissao>();
		for (String acesso : getAcessosOrdenadas()) {
			for (Permissao acess : Permissao.values()) {
				if (acesso.equalsIgnoreCase(acess.name())) {
					permissoes.add(acess);
				}
			}
		}
		return permissoes;
	}
	
	public Set<String> getAcessos() {
		return acessos;
	}
	
	public List<String> getAcessosOrdenadas() {
		List<String> retorno = new ArrayList<String>();
		for (String acesso : acessos) {
			retorno.add(acesso);
		}
		Collections.sort(retorno);
		return retorno;
	}
	
	public String getTexto_ativo() {
		return texto_ativo;
	}

	public void setTexto_ativo(String texto_ativo) {
		this.texto_ativo = texto_ativo;
	}

	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}
	

	public int getVersionNum() {
		return versionNum;
	}

	public TipoCadastro getEnt_tipo() {
		return ent_tipo;
	}

	public void setEnt_tipo(TipoCadastro ent_tipo) {
		this.ent_tipo = ent_tipo;
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

	public String getEnt_cep() {
		return ent_cep;
	}

	public void setEnt_cep(String ent_cep) {
		this.ent_cep = ent_cep;
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
		 if (this.ent_inativo == true){
			this.setTexto_ativo("ATIVO");
		}else if (this.ent_inativo == false){
			this.setTexto_ativo("INATIVO");
		} 
		
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
	
	public String getEnt_endereco() {
		return ent_endereco;
	}

	public void setEnt_endereco(String ent_endereco) {
		this.ent_endereco = ent_endereco;
	}

	public String getEnt_cpf() {
		return ent_cpf;
	}

	public void setEnt_cpf(String ent_cpf) {
		this.ent_cpf = ent_cpf;
	}

	public String getEnt_celular() {
		return ent_celular;
	}

	public void setEnt_celular(String ent_celular) {
		this.ent_celular = ent_celular;
	}

	public String getEnt_idtmilitar() {
		return ent_idtmilitar;
	}

	public void setEnt_idtmilitar(String ent_idtmilitar) {
		this.ent_idtmilitar = ent_idtmilitar;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_codigo", ent_codigo);
		map.put("ent_nome", ent_nome);
		return new JSONObject(map);
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
		Pessoa other = (Pessoa) obj;
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
				+ ent_nome + ", ent_idade=" + ent_idade + ", ent_endereco="
				+ ent_endereco + ", ent_cpf=" + ent_cpf + ", ent_celular="
				+ ent_celular + ", ent_cep=" + ent_cep + ", ent_idtmilitar="
				+ ent_idtmilitar + ", ent_n_guerra=" + ent_n_guerra
				+ ", ent_peso=" + ent_peso + ", ent_altura=" + ent_altura
				+ ", ent_tipo=" + ent_tipo + ", graduacao=" + graduacao
				+ ", acessos=" + acessos + "]";
	}

	
	

}
