package br.com.project.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Respons�vel  pela permissao dos usuarios no sistema
 * Usu�rios ADMINISTRADOR, AUXILIAR, M�DICO
 * @author Juan Campos
 *
 */


public enum Permissao {
	
	ADMIN("ADMIN", "Administrador"),
	USER("USER","Usu�rio Padr�o"), 
	CADASTRO_ACESSAR("CADASTRO_ACESSAR", "Cadastro - Acessar"),    
	
		
	PRODUTO_ACESSAR("PRODUTO_ACESSAR", "Produto - Acessar"),
	PRODUTO_NOVO("PRODUTO_NOVO", "Produto - Novo"),
	PRODUTO_EDITAR("PRODUTO_EDITAR", "Produto - Editar"),
	PRODUTO_EXCLUIR("PRODUTO_EXCLUIR", "Produto - Excluir"),
	
	
	USUARIO_ACESSAR("USUARIO_ACESSAR", "Usuario - Acessar"),
	USUARIO_NOVO("USUARIO_NOVO", "Usuario - Novo"),
	USUARIO_EDITAR("USUARIO_EDITAR", "Usuario  - Editar"),
	USUARIO_EXCLUIR("USUARIO_EXCLUIR", "Usuario  - Excluir"),
	
	PACIENTE_ACESSAR("PACIENTE_ACESSAR", "Paciente - Acessar"),
	PACIENTE_NOVO("PACIENTE_NOVO", "Paciente - Novo"),
	PACIENTE_EDITAR("PACIENTE_EDITAR", "Paciente - Editar"),
	PACIENTE_EXCLUIR("PACIENTE_EXCLUIR", "Paciente - Excluir"),
	
	MEDICO_ACESSAR("MEDICO_ACESSAR", "Medico - Acessar"),
	MEDICO_NOVO("MEDICO_NOVO", "Medico - Novo"),
	MEDICO_EDITAR("MEDICO_EDITAR", "Medico - Editar"),
	MEDICO_EXCLUIR("MEDICO_EXCLUIR", "Medico - Excluir"),
	
	//GERA RELATORIO ESTOQUE
	ESTOQUE_GERAR("ESTOQUE_GERAR", "Estoque - Gerar"),
	
	//GERA RELATORIO PACIENTE
	PACIENTE_GERAR("PACIENTE_GERAR", "Paciente - Gerar"),
	
	//GERA RECEITUARIO
	RECEITUARIO_GERAR("RECEITUARIO_GERAR", "Receituario - Gerar");
	
	
	
	
	
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
