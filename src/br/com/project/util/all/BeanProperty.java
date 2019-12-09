package br.com.project.util.all;

import java.io.Serializable;

/**
 * Usada para estabelecer o ambiente que o projeto está sendo executado(Produção ou teste)
 * @author Juan Campos
 *
 */
public class BeanProperty implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String springTest = "";

	public BeanProperty(String springTest) {
		System.setProperty("springTest", springTest);
		this.springTest = springTest;
	}

	public String getSpringTest() {
		return springTest;
	}

}
