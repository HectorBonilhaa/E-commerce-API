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

import br.com.serratec.eCommerce.model.ItemPedido;
import br.com.serratec.eCommerce.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@RequestMapping("/teste/itemPedidos") 
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService servico;
	

	
	@GetMapping
	@ApiOperation(value="Obter todos os itens", notes="Itens")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os itens"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ItemPedido>> obterTodos(){
		List<ItemPedido> lista = servico.obterTodos()
;		return ResponseEntity.ok(lista); //200
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Obter Item", notes="Item")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um item"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedido> obterPorId(@PathVariable Long id){
		Optional<ItemPedido> optItemPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optItemPedido.get()); //200
	}
	
	@PostMapping 
	@ApiOperation(value="Inserir Item", notes="Inserir Item")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Item Adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedido> cadastrar(@RequestBody ItemPedido itemPedido) {
		itemPedido = servico.cadastrar(itemPedido);
		return new ResponseEntity<>(itemPedido, HttpStatus.CREATED); //201
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualizar item", notes="Atualizar item")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Item Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
		return ResponseEntity.ok(servico.atualizar(id, itemPedido)); //200
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deletar item", notes="Remover Item")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Item Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
	

}
