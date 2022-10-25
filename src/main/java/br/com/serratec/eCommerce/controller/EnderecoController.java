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

import br.com.serratec.eCommerce.model.Endereco;
import br.com.serratec.eCommerce.service.EnderecoService;

@RestController
@RequestMapping("/teste/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService servico;

	@GetMapping
	public ResponseEntity<List<Endereco>> obterTodos() {
		List<Endereco> lista = servico.obterTodos();
		return ResponseEntity.ok(lista); // 200
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> obterPorId(@PathVariable Long id) {
		Optional<Endereco> optEndereco = servico.obterPorId(id);
		return ResponseEntity.ok(optEndereco.get()); // 200
	}

	@PostMapping
	public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco) {
		
		// executa o metodo de buscar no via cep
		//Consertando endereço
		Endereco enderecoCep = servico.consultaCep(endereco);
		enderecoCep.setNumero(endereco.getNumero());
		enderecoCep.setCliente(endereco.getCliente());
		enderecoCep = servico.cadastrar(enderecoCep);
		return new ResponseEntity<>(enderecoCep, HttpStatus.CREATED); // 201

	}

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
		return ResponseEntity.ok(servico.atualizar(id, endereco)); // 200
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}

}
