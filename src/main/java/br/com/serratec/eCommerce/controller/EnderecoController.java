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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/teste/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService servico;

	@GetMapping
	@ApiOperation(value="Obter todos os endereços", notes="Endereços")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os endereços"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<Endereco>> obterTodos() {
		List<Endereco> lista = servico.obterTodos();
		return ResponseEntity.ok(lista); // 200
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Obter Endereço", notes="Endereço")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um endereço"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Endereco> obterPorId(@PathVariable Long id) {
		Optional<Endereco> optEndereco = servico.obterPorId(id);
		return ResponseEntity.ok(optEndereco.get()); // 200
	}

	@PostMapping
	@ApiOperation(value="Inserir Endereço", notes="Inserir Endereço")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Endereço Adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco) {

        // executa o metodo de buscar no via cep
        //Consertando endereço
        Endereco enderecoCep = servico.consultaCep(endereco);
        enderecoCep.setNumero(endereco.getNumero());
        enderecoCep.setCliente(endereco.getCliente());
        enderecoCep.setComplemento(endereco.getComplemento());
        enderecoCep = servico.cadastrar(enderecoCep);
        return new ResponseEntity<>(enderecoCep, HttpStatus.CREATED); // 201

    }

	@PutMapping("/{id}")
	@ApiOperation(value="Atualizar endereço", notes="Atualizar endereço")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Endereço Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
		return ResponseEntity.ok(servico.atualizar(id, endereco)); // 200
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value="Deletar endereço", notes="Remover Endereço")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Endereço Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}

}
