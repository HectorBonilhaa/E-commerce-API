package br.com.serratec.eCommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.eCommerce.model.Categoria;
import br.com.serratec.eCommerce.service.CategoriaService;


@RestController
@RequestMapping("/teste/categorias") 
public class CategoriaController {
	
	@Autowired
	private CategoriaService servico;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> obterTodos(){
		List<Categoria> lista = servico.obterTodos()
;		return ResponseEntity.ok(lista); //200
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obterPorId(@PathVariable Long id){
		Optional<Categoria> optCategoria = servico.obterPorId(id);
		return ResponseEntity.ok(optCategoria.get()); //200
	}
	
	@PostMapping 
	public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria categoria) {
		categoria = servico.cadastrar(categoria);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED); //201
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
		return ResponseEntity.ok(servico.atualizar(id, categoria)); //200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
	

}