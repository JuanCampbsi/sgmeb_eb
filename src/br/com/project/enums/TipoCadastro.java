package br.com.project.enums;

public enum TipoCadastro {

	TIPO_CADASTRO_CONSTRUTORA("Construtora"),
	TIPO_CADASTRO_CLIENTE("Cliente"),
	TIPO_CADASTRO_FORNECEDOR("Fornecedor"),
	TIPO_CADASTRO_FUNCIONARIO("Funcionário"),
	TIPO_CADASTRO_VENDEDOR("Vendedor");

	private String tipo = "";

	private TipoCadastro(String tipo) {
		this.tipo = tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return getTipo();
	}
	
	public String getName() {
		return this.name();
	}

}
