package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.exception.ResourceBadRequestException;
import br.com.serratec.eCommerce.exception.ResourceNotFoundException;
import br.com.serratec.eCommerce.model.Categoria;
import br.com.serratec.eCommerce.repository.CategoriaRepository;


@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repositorio;
	
	public List<Categoria> obterTodos(){
		return repositorio.findAll();
	}
	
	public Optional<Categoria> obterPorId(Long id){
		Optional<Categoria> optCategoria = repositorio.findById(id);
		
		if(optCategoria.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível encontrar a categoria com id " + id);
		}
		
		return optCategoria;
	}
	
	public Categoria cadastrar(Categoria categoria) {
		validarModelo(categoria);
		categoria.setId(null);
		return repositorio.save(categoria);
	}
	
	public Categoria atualizar(Long id, Categoria categoria) {
		
		obterPorId(id);
		
		validarModelo(categoria);
		
		categoria.setId(id);
		return repositorio.save(categoria);
	}
	public void deletar(Long id) {
		obterPorId(id);
		
		repositorio.deleteById(id);
	}
	
	private void validarModelo(Categoria categoria) {
		if(categoria.getNome() == null) {
			throw new ResourceBadRequestException("A categoria deve ser informada");
	
	}
		
	}
	
	
	}
