package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.model.Cliente;
import br.com.serratec.eCommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository repositorio;

	public List<Cliente> listarTodos() {
		return repositorio.findAll();
	}

	public Optional<Cliente> procurarId(Long id) {
		return repositorio.findById(id);
	}

	public Cliente cadastrar(Cliente cliente) {

		cliente.setId(null);
		return repositorio.save(cliente);

	}

	public Cliente atualizar(Long id, Cliente cliente) {

		cliente.setId(id);
		return repositorio.save(cliente);

	}

	public void deletar(Long id) {
		repositorio.deleteById(id);

	}
}
