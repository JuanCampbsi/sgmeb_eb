package br.com.project.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Responsável  pela permissao dos usuarios no sistema
 * Usuários ADMINISTRADOR, AUXILIAR, MÉDICO
 * @author Juan Campos
 *
 */


public enum Permissao {
	
	ADMIN("ADMIN", "Administrador"),
	USER("USER","Usuário Padrão"), 
	CADASTRO_ACESSAR("CADASTRO_ACESSAR", "Cadastro - Acessar"),    
	
		
	PRODUTO_ACESSAR("PRODUTO_ACESSAR", "Produto - Acessar"),
	PRODUTO_NOVO("PRODUTO_NOVO", "Produto - Novo"),
	PRODUTO_EDITAR("PRODUTO_EDITAR", "Produto - Editar"),
	PRODUTO_EXCLUIR("PRODUTO_EXCLUIR", "Produto - Excluir"),
	
	
	AUXILIAR_ACESSAR("AUXILIAR_ACESSAR", "Auxiliar - Acessar"),
	AUXILIAR_NOVO("AUXILIAR_NOVO", "Auxiliar - Novo"),
	AUXILIAR_EDITAR("AUXILIAR_EDITAR", "Auxiliar - Editar"),
	AUXILIAR_EXCLUIR("AUXILIAR_EXCLUIR", "Auxiliar - Excluir"),
	
	//GERA RELATORIO ESTOQUE
	ESTOQUE_GERAR("ESTOQUE_GERAR", "Estoque - Gerar"),
	
	//GERA RELATORIO PACIENTE
	PACIENTE_GERAR("PACIENTE_GERAR", "Paciente - Gerar"),
	
	//GERA RECEITUARIO
	RECEITUARIO_GERAR("RECEITUARIO_GERAR", "Receituario - Gerar"),
	
	
	
	APPLET_FILE_LOCAL("APPLET_FILE_LOCAL", "Habilitar - Applet");
	
	private String valor = "";
	private String descricao = "";

	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}

	private Permissao() {
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return getValor();
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public static List<Permissao> getListPermissao() {
		List<Permissao> permissoes = new ArrayList<Permissao>();
		for (Permissao permissao : Permissao.values()) {
			permissoes.add(permissao);
		}
		Collections.sort(permissoes, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
		});
		return permissoes;
	}

}
