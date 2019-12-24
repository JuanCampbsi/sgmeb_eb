package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import br.com.project.listener.CustomListener;

/**
 * Reprensenta a tabela de revision log em nivel de aplicação do hibernate
 * envers
 * 
 * @author Juan Campos
 * 
 */
@Entity
@Table(name = "revinfo")
@RevisionEntity(CustomListener.class)
public class InformacaoRevisao extends DefaultRevisionEntity implements
		Serializable {

	private static final long serialVersionUID = 1L;

	
	@ForeignKey(name = "pessoa_fk")
	@JoinColumn(nullable = false, name = "pessoa")
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
