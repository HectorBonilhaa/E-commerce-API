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

import br.com.serratec.eCommerce.model.Pedido;
import br.com.serratec.eCommerce.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/teste/pedidos") 
public class PedidoController {
	
	@Autowired
	private PedidoService servico;
	
	@GetMapping
	@ApiOperation(value="Obter todos os pedidos", notes="Pedidos")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os pedidos"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<Pedido>> obterTodos(){
		List<Pedido> lista = servico.obterTodos()
;		return ResponseEntity.ok(lista); //200
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Obter Pedido", notes="Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um pedido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Pedido> obterPorId(@PathVariable Long id){
		Optional<Pedido> optPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optPedido.get()); //200
	}
	
	@PostMapping 
	@ApiOperation(value="Inserir Pedido", notes="Inserir Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Pedido Adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido) {
		pedido = servico.cadastrar(pedido);
		return new ResponseEntity<>(pedido, HttpStatus.CREATED); //201
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualizar pedido", notes="Atualizar pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Pedido Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
		return ResponseEntity.ok(servico.atualizar(id, pedido)); //200
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deletar pedido", notes="Remover Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Pedido Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
	

}
