package br.com.project.model.classes;

import java.io.Serializable;


public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	
	protected  Boolean pes_inativo = false;	
	
	protected  String pes_nome;	
	
	protected  int pes_idade;	
	
	protected  String pes_genero;	
	
	protected  String pes_posto;	
	
	protected  String pes_grad;
	
	protected  String nome_guerra;		
	
	protected  String aux_coren;	
	
	protected  String aux_profissao;
	


	public Boolean getPes_inativo() {
		return pes_inativo;
	}


	public void setPes_inativo(Boolean pes_inativo) {
		this.pes_inativo = pes_inativo;
	}


	public String getPes_nome() {
		return pes_nome;
	}


	public void setPes_nome(String pes_nome) {
		this.pes_nome = pes_nome;
	}


	public int getPes_idade() {
		return pes_idade;
	}


	public void setPes_idade(int pes_idade) {
		this.pes_idade = pes_idade;
	}


	public String getPes_genero() {
		return pes_genero;
	}


	public void setPes_genero(String pes_genero) {
		this.pes_genero = pes_genero;
	}


	public String getPes_posto() {
		return pes_posto;
	}


	public void setPes_posto(String pes_posto) {
		this.pes_posto = pes_posto;
	}


	public String getPes_grad() {
		return pes_grad;
	}


	public void setPes_grad(String pes_grad) {
		this.pes_grad = pes_grad;
	}


	public String getNome_guerra() {
		return nome_guerra;
	}


	public void setNome_guerra(String nome_guerra) {
		this.nome_guerra = nome_guerra;
	}


	public String getAux_coren() {
		return aux_coren;
	}


	public void setAux_coren(String aux_coren) {
		this.aux_coren = aux_coren;
	}


	public String getAux_profissao() {
		return aux_profissao;
	}


	public void setAux_profissao(String aux_profissao) {
		this.aux_profissao = aux_profissao;
	}
	
	

	

	
}
