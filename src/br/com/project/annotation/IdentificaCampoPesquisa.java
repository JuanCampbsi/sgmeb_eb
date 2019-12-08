package br.com.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation  para identificar os campos que podem aparecer na pesquisa das
 * telas
 * 
 * @author Juan Campos
 *
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificaCampoPesquisa {
	String descricaoCampo(); // descricao do campos para a tela
	String campoConsulta(); // campo do banco
	int principal() default 10000; // posicao que vai aparecer no combo, 10000
									// para que o default seja colocado depois
									// dos que sao estabelecidos
}
