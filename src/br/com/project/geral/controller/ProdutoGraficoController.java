package br.com.project.geral.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.sun.faces.action.RequestMapping;


@Controller
@RequestMapping
public class ProdutoGraficoController implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@RequestMapping("/gerarGraficoInicial")
	public ResponseEntity<List<Teste>> trial(){
		
		Teste teste1 = new Teste(id: 1L, valor: "Teste1");
		Teste teste2 = new Teste (id: 2L, valor: "Teste2");
		Teste teste3 = new Teste (id: 3L, valor: "Teste3");
		Teste teste4 = new Teste (id: 4L, valor: "Teste4");
		
		List<Teste> lista = new ArrayList<>();
		
		lista.add(teste1);
		lista.add(teste2);
		lista.add(teste3);
		lista.add(teste4);
		
		return ResponseEntity.ok(lista):
	}
	
	class Teste{
		private Long id;
		private String valor;
		
		public Teste (Long id, String valor){
			this.id = id;
			this.valor = valor;
		} 
		
		public Long getId(){
			return id;
		}
		
	}
	
	

}