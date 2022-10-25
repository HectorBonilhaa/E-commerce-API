package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.eCommerce.exception.ResourceBadRequestException;
import br.com.serratec.eCommerce.exception.ResourceNotFoundException;
import br.com.serratec.eCommerce.model.Endereco;
import br.com.serratec.eCommerce.repository.EnderecoRepository;


@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repositorio;
	
	public List<Endereco> obterTodos(){
		return repositorio.findAll();
	}
	
	public Optional<Endereco> obterPorId(Long id){
		Optional<Endereco> optEndereco = repositorio.findById(id);
		
		if(optEndereco.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível encontrar a endereco com id " + id);
		}
		
		return optEndereco;
	}
	
//	public Endereco cadastrar(Endereco endereco) {
//		validarModelo(endereco);
//		//Se não tem Id ele cadastra, se tem Id ele atualiza
//		endereco.setId(null);
//		
//		return repositorio.save(endereco);
//	}
//	
	public Endereco atualizar(Long id, Endereco endereco) {
		
		//Usando o método somente para validar se existe algo com o id informado
		obterPorId(id);
		
		validarModelo(endereco);
		
		endereco.setId(id);
		return repositorio.save(endereco);
	}
	public void deletar(Long id) {
		//Usando o método somente para validar se existe algo com o id informado
		obterPorId(id);
		
		repositorio.deleteById(id);
	}
	
	private void validarModelo(Endereco endereco) {
		if(endereco.getCep() == null) {
			throw new ResourceBadRequestException("O CEP deve ser informado");
	
	}
		
	}
	
//	Método para fazer consulta na api do via cep //
	public Endereco consultaCep(Endereco endereco) {
		return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + endereco.getCep() + "/json/", Endereco.class).getBody();
	}

	
	}
