var arrayIdsElementsPage = new Array;
var idundefined = 'idundefined';
var classTypeString = 'java.lang.String';
var classTypeLong = 'java.lang.Long';
var classTypeDate = 'java.util.Date';
var classTypeBoolean = 'java.lang.Boolean';
var classTypeBigDecimal = 'java.math.BigDecimal';

function reloadPage() {
	$(function() {
		location.reload();
	});
	
}	

function validaDescricao(descricao) {
	if (descricao === ' ' || descricao.trim() === '') {
		return "Descrição não foi informada.";
	}
	 else {
		return descricao;
	}
}


// invalida a session do spring security
function logout(contextPath) {
	
	document.location =	 contextPath + '/j_spring_security_logout';
	var post = 'invalidar_session';
	$.ajax(
		{ 
		  type: "POST", 
		  url: post
		});
}

/**
 * Usada apenas para o menu do sistema Limpar variaveis por ajax e redireciona
 * sempre a pagina
 * 
 * @param context
 * @param pagina
 * @param post
 */
