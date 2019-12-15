package br.com.project.enums;

public enum TituloSituacao {

	TITULO_ABERTO("Aberto"),
	TITULO_BAIXADO("Baixado");

	private String tipo = "";

	private TituloSituacao(String tipo) {
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

}
