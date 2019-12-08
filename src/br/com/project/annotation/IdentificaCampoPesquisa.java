package br.com.project.annotation;

import java.lang.annotation.Retention;

import org.hibernate.annotations.Target;

/**
 * Annotation para identificar os campos que podem aparecer na pesquisa das
 * telas
 * 
 * @author Juan Campos
 *
 */
@Target(value = { java.lang.annotation.ElementType.FIELD })
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
public abstract @interface IdentificaCampoPesquisa {
	String descricaoCampo(); // descri��o do campos para a tela
	String campoConsulta(); // campo do banco
	int principal() default 10000; // posi��o que vai aparecer no combo, 10000 � para que o default seja colocado depois dos que s�o estabelecidos
}
