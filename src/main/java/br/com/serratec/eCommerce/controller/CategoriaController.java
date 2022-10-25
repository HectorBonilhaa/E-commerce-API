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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/teste/categorias") 
public class CategoriaController {
	
	@Autowired
	private CategoriaService servico;
	
	@GetMapping
	@ApiOperation(value="Obter todas as categorias", notes="Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todas as categorias"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<Categoria>> obterTodos(){
		List<Categoria> lista = servico.obterTodos()
;		return ResponseEntity.ok(lista); //200
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Obter Categoria", notes="Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna uma categoria"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Categoria> obterPorId(@PathVariable Long id){
		Optional<Categoria> optCategoria = servico.obterPorId(id);
		return ResponseEntity.ok(optCategoria.get()); //200
	}
	
	@PostMapping 
	@ApiOperation(value="Inserir Categoria", notes="Inserir Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Categoria adcionada"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})

	public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria categoria) {
		categoria = servico.cadastrar(categoria);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED); //201
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualizar categoria", notes="Atualizar Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Categoria Atualizada"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
		return ResponseEntity.ok(servico.atualizar(id, categoria)); //200
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deletar categoria", notes="Remover Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Categoria Removida"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
	

}
