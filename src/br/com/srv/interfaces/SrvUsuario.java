package br.com.srv.interfaces;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public interface SrvUsuario  extends Serializable{

	Date getUltimoAcessoUsuarioLogado(String name);

	void updateUltimoAcessoUser(String login);

	boolean existeUsuario(String user_login);

}
