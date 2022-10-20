package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.model.Endereco;
import br.com.serratec.eCommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

	private EnderecoRepository repositorio;

	public List<Endereco> listarTodos() {
		return repositorio.findAll();
	}

	public Optional<Endereco> procurarId(Long id) {
		return repositorio.findById(id);
	}

	public Endereco cadastrar(Endereco endereco) {

		endereco.setId(null);
		return repositorio.save(endereco);

	}

	public Endereco atualizar(Long id, Endereco endereco) {

		endereco.setId(id);
		return repositorio.save(endereco);

	}

	public void deletar(Long id) {
		repositorio.deleteById(id);

	}
}
