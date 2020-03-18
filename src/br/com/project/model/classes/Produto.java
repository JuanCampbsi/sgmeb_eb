package br.com.project.model.classes;

import java.beans.Transient;
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

import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "produto")
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", initialValue = 1, allocationSize = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	private Long prod_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "prod_nome")
	@Column(nullable = true)
	private String prod_nome;

	@Column(nullable = true)
	private String prod_tipo;

	@Column(nullable = true)
	private String descricao_prod;
	
	@Column(nullable = true)
	private String prod_prazo;

	@Column(unique = true)
	private String serie_prod;
	

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date prod_datacadastro = new Date();

	@Version
	@Column(name = "versionNum")
	private int versionNum;


	public String getProd_prazo() {
		return prod_prazo;
	}

	public void setProd_prazo(String prod_prazo) {
		this.prod_prazo = prod_prazo;
	}

	public Long getProd_codigo() {
		return prod_codigo;
	}

	public void setProd_codigo(Long prod_codigo) {
		this.prod_codigo = prod_codigo;
	}

	public String getProd_nome() {
		return prod_nome;
	}

	public void setProd_nome(String prod_nome) {
		this.prod_nome = prod_nome;
	}

	public String getProd_tipo() {
		return prod_tipo;
	}

	public void setProd_tipo(String prod_tipo) {
		this.prod_tipo = prod_tipo;
	}

	public String getDescricao_prod() {
		return descricao_prod;
	}

	public void setDescricao_prod(String descricao_prod) {
		this.descricao_prod = descricao_prod;
	}

	public String getSerie_prod() {
		return serie_prod;
	}

	public void setSerie_prod(String serie_prod) {
		this.serie_prod = serie_prod;
	}

	public Date getFabri_prod() {
		return fabri_prod;
	}

	public void setFabri_prod(Date fabri_prod) {
		this.fabri_prod = fabri_prod;
	}

	public Date getProd_datacadastro() {
		return prod_datacadastro;
	}

	public void setProd_datacadastro(Date prod_datacadastro) {
		this.prod_datacadastro = prod_datacadastro;
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
				+ ((prod_codigo == null) ? 0 : prod_codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (prod_codigo == null) {
			if (other.prod_codigo != null)
				return false;
		} else if (!prod_codigo.equals(other.prod_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [prod_codigo=" + prod_codigo + ", prod_nome="
				+ prod_nome + ", prod_tipo=" + prod_tipo + ", descricao_prod="
				+ descricao_prod + ", principio_ativo=" + ", serie_prod="
				+ serie_prod + ", prod_datacadastro=" + prod_datacadastro
				+ ", fabri_prod=" + fabri_prod + ", data_atual=" + data_atual
				+ ", validade_prod=" + validade_prod + ", diferencaEmDias="
				+ diferencaEmDias + "]";
	}

	@Temporal(TemporalType.DATE)
	private Date fabri_prod;

	@Temporal(TemporalType.DATE)
	private Date data_atual = new Date();

	@Temporal(TemporalType.DATE)
	private Date validade_prod;

	public Date getData_atual() {
		return data_atual;
	}

	public void setData_atual(Date data_atual) {
		this.data_atual = data_atual;
	}

	public Date getValidade_prod() {
		return validade_prod;
	}

	public void setValidade_prod(Date validade_prod) {
		this.validade_prod = validade_prod;
	}

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "diferencaEmDias", principal = 1)
	private Double diferencaEmDias;

	public Double getDiferencaEmDias() {
		return diferencaEmDias;
	}

	public void setDiferencaEmDias(Double diferencaEmDias) {
		this.diferencaEmDias = diferencaEmDias;
	}
	
	
	private Double diferencaEmDiasMaior;
	

	public Double getDiferencaEmDiasMaior() {
		return diferencaEmDiasMaior;
	}

	public void setDiferencaEmDiasMaior(Double diferencaEmDiasMaior) {
		this.diferencaEmDiasMaior = diferencaEmDiasMaior;
	}

	public double validar() throws Exception {

		double result = 0;
		long diferenca = getValidade_prod().getTime()
				- getData_atual().getTime();
		double dif = (diferenca / 1000) / 60 / 60 / 24; // resultado é diferença
														// entre as datas em
														// dias
		long horasRestantes = (diferenca / 1000) / 60 / 60 % 24; // calcula as
																	// horas
																	// restantes
		result = dif + (horasRestantes / 24d); // transforma as horas restantes
		// em fração de dias
		
		
		if (result < 30) {
			this.setDiferencaEmDias((double) 1);			
			this.setProd_prazo("30 Dias/Vencimento");

		} else {
			this.setDiferencaEmDias((double) 31);
			this.setProd_prazo("Valido");
		}
		return diferencaEmDias;
	}
	
	
	public double validar60() throws Exception {

		double result = 0;
		long diferenca = getValidade_prod().getTime()
				- getData_atual().getTime();
		double dif = (diferenca / 1000) / 60 / 60 / 24; // resultado é diferença
														// entre as datas em
														// dias
		long horasRestantes = (diferenca / 1000) / 60 / 60 % 24; // calcula as
																	// horas
																	// restantes
		result = dif + (horasRestantes / 24d); // transforma as horas restantes
		// em fração de dias
		
		if(result < 60){
			this.setDiferencaEmDiasMaior((double)6);
			this.setProd_prazo("60 Dias/Vencimento");
		}else{
			this.setDiferencaEmDiasMaior((double)61);
			this.setProd_prazo("Valido");
		}

		return diferencaEmDiasMaior;
	}
	

	@Transient
	public boolean isValido() {
		return getDiferencaEmDias() == 31;

	}

	
	@Transient
	public boolean isInvalido() throws Exception {
		validar();
		return !this.isValido();
	}
	
	@Transient
	public boolean isValido60() {
		return getDiferencaEmDiasMaior() == 61;

	}

	@Transient
	public boolean isInvalido60() throws Exception {
		validar60();
		return !this.isValido60();
	}
	


}