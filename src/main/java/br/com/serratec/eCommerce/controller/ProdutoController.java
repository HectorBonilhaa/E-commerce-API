package br.com.serratec.eCommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.eCommerce.dto.ProdutoRequestDTO;
import br.com.serratec.eCommerce.dto.ProdutoResponseDTO;
import br.com.serratec.eCommerce.service.ProdutoService;


@RestController
@RequestMapping("/teste/produtos") 
public class ProdutoController {
	
	@Autowired
	private ProdutoService servico;
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodos(){
		List<ProdutoResponseDTO> lista = servico.obterTodos()
;		return ResponseEntity.ok(lista); //200
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id){
		Optional<ProdutoResponseDTO> optProduto = servico.obterPorId(id);
		return ResponseEntity.ok(optProduto.get()); //200
	}
	
	@PostMapping (
	consumes =  { MediaType.APPLICATION_JSON_VALUE ,MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { "application/json" })
	public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestPart("produto") ProdutoRequestDTO produto, @RequestPart("foto") MultipartFile foto) {

		
		ProdutoResponseDTO produtoDTO = servico.cadastrar(produto);
		servico.salvarFoto(foto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED); //201
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produto) {
		return ResponseEntity.ok(servico.atualizar(id, produto)); //200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
	

}
