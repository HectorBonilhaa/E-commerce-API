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

import br.com.serratec.eCommerce.model.Cliente;
import br.com.serratec.eCommerce.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/teste/clientes") 
public class ClienteController {
	
	@Autowired
	private ClienteService servico;
	
	@GetMapping
	@ApiOperation(value="Obter todos os clientes", notes="Clientes")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os clientes"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<Cliente>> obterTodos(){
		List<Cliente> lista = servico.obterTodos()
;		return ResponseEntity.ok(lista); //200
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Obter Cliente", notes="Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um cliente"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Cliente> obterPorId(@PathVariable Long id){
		Optional<Cliente> optCliente = servico.obterPorId(id);
		return ResponseEntity.ok(optCliente.get()); //200
	}
	
	@PostMapping 
	@ApiOperation(value="Inserir Cliente", notes="Inserir Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Cliente Adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})

	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
		cliente = servico.cadastrar(cliente);
		return new ResponseEntity<>(cliente, HttpStatus.CREATED); //201
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualizar cliente", notes="Atualizar cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		return ResponseEntity.ok(servico.atualizar(id, cliente)); //200
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deletar cliente", notes="Remover Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
	

}
