package br.com.project.geral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Usuario;
import br.com.srv.interfaces.SrvUsuario;

@Controller
public class UsuarioController extends ImplementacaoCrud<Usuario> implements
		InterfaceCrud<Usuario> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SrvUsuario srvUsuario;

	public Usuario findUserLogado(String userLogado) throws Exception {
		return super.findUninqueByProperty(Usuario.class, userLogado,
				"user_login", " and entity.user_inativo is false ");
	}

	public Date getUltimoAcessoUsuarioLogada(String login) {
		return srvUsuario.getUltimoAcessoUsuarioLogado(login);
	}

	public void updateUltimoAcessoUser(String login) {
		srvUsuario.updateUltimoAcessoUser(login);
	}

	

	public boolean existeUsuario(String user_login) {
		return srvUsuario.existeUsuario(user_login);
	}

}
