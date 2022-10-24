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


@RestController
@RequestMapping("/teste/pedidos") 
public class PedidoController {
	
	@Autowired
	private PedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> obterTodos(){
		List<Pedido> lista = servico.obterTodos()
;		return ResponseEntity.ok(lista); //200
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> obterPorId(@PathVariable Long id){
		Optional<Pedido> optPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optPedido.get()); //200
	}
	
	@PostMapping 
	public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido) {
		pedido = servico.cadastrar(pedido);
		return new ResponseEntity<>(pedido, HttpStatus.CREATED); //201
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
		return ResponseEntity.ok(servico.atualizar(id, pedido)); //200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
	

}
