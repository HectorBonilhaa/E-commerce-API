package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.exception.ResourceBadRequestException;
import br.com.serratec.eCommerce.exception.ResourceNotFoundException;
import br.com.serratec.eCommerce.model.Cliente;
import br.com.serratec.eCommerce.repository.ClienteRepository;


@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repositorio;
	
	public List<Cliente> obterTodos(){
		return repositorio.findAll();
	}
	
	public Optional<Cliente> obterPorId(Long id){
		Optional<Cliente> optCliente = repositorio.findById(id);
		
		if(optCliente.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível encontrar a cliente com id " + id);
		}
		
		return optCliente;
	}
	
	public Cliente cadastrar(Cliente cliente) {
		validarNomeUsuario(cliente);
		validarNomeCompleto(cliente);
		validarCpf(cliente);
		validarEmail(cliente);
		cliente.setId(null);
		return repositorio.save(cliente);
	}
	
	public Cliente atualizar(Long id, Cliente cliente) {
		obterPorId(id);
		validarNomeUsuario(cliente);
		validarNomeCompleto(cliente);
		validarCpf(cliente);
		validarEmail(cliente);
		cliente.setId(id);
		return repositorio.save(cliente);
	}
	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}
	
	private void validarNomeUsuario(Cliente cliente) {
		if(cliente.getNomeUsuario() == null) {
			throw new ResourceBadRequestException("O nome de usuário deve ser informado");
		}	
	}
	
	private void validarNomeCompleto(Cliente cliente) {
		if(cliente.getNomeCompleto() == null) {
			throw new ResourceBadRequestException("O nome deve ser informado");
		}	
	}
	
	private void validarCpf(Cliente cliente) {
		List<Cliente> clientes = repositorio.findByCpf(cliente.getCpf());
		if(cliente.getNomeUsuario() == null) {
			throw new ResourceBadRequestException("O cpf deve ser informado");
		}else if(clientes.size()>0) {
			throw new ResourceBadRequestException("Este cpf já foi cadastrado");
		}
	}
	
	private void validarEmail(Cliente cliente) {
		List<Cliente> clientes = repositorio.findByEmail(cliente.getEmail());
		if(cliente.getEmail() == null) {
			throw new ResourceBadRequestException("O e-mail deve ser informado");
		}else if(clientes.size()>0) {
			throw new ResourceBadRequestException("Este e-mail já foi cadastrado");
		}
	}
	
	
	}
