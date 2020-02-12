package br.com.project.enums;

public enum TipoCadastro {

	TIPO_CADASTRO_USUARIO("Usuario"),
	TIPO_CADASTRO_PACIENTE("Paciente");
	

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
