package br.com.serratec.eCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.eCommerce.model.Endereco;
import br.com.serratec.eCommerce.model.ViaCep;
import br.com.serratec.eCommerce.repository.EnderecoRepository;

/**
 * EnderecoController
 */
@RestController
@RequestMapping("/teste/enderecos")
@CrossOrigin(origins = "*")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {
       
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable Long id) {
       
        var endereco = repository.findById(id);
        return ResponseEntity.ok(endereco.get());
    }

    @PostMapping
    public ResponseEntity<Endereco> post(@RequestParam String cep) {

        ViaCep viaCep = getEnderecoByCep(cep);
        Endereco endereco = new Endereco();
        endereco.viaCepEnderecoConverter(viaCep);

        repository.save(endereco);

		return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    @GetMapping("/viaCep")
    public ViaCep getEnderecoByCep(@RequestParam String cep){

        RestTemplate restTemplate = new RestTemplate();
		ViaCep viaCep = restTemplate.getForObject("http://viacep.com.br/ws/"+ cep +"/json/", ViaCep.class);

        return viaCep;
    }
}
