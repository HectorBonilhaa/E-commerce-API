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
import br.com.serratec.eCommerce.model.Pedido;
import br.com.serratec.eCommerce.model.Produto;
import br.com.serratec.eCommerce.service.ItemPedidoService;
import br.com.serratec.eCommerce.service.ProdutoService;


@RestController
@RequestMapping("/teste/itemPedidos") 
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService servico;
	

	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> obterTodos(){
		List<ItemPedido> lista = servico.obterTodos()
;		return ResponseEntity.ok(lista); //200
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> obterPorId(@PathVariable Long id){
		Optional<ItemPedido> optItemPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optItemPedido.get()); //200
	}
	
	@PostMapping 
	public ResponseEntity<ItemPedido> cadastrar(@RequestBody ItemPedido itemPedido) {
		itemPedido = servico.cadastrar(itemPedido);
		return new ResponseEntity<>(itemPedido, HttpStatus.CREATED); //201
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
		return ResponseEntity.ok(servico.atualizar(id, itemPedido)); //200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
	

}
