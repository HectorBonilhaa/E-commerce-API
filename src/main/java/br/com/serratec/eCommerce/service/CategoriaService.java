package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.model.Categoria;
import br.com.serratec.eCommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository repositorio;

	public List<Categoria> listarTodos() {
		return repositorio.findAll();
	}

	public Optional<Categoria> procurarId(Long id) {
		return repositorio.findById(id);
	}

	public Categoria cadastrar(Categoria categoria) {

		categoria.setId(null);
		return repositorio.save(categoria);

	}

	public Categoria atualizar(Long id, Categoria categoria) {

		categoria.setId(id);
		return repositorio.save(categoria);

	}

	public void deletar(Long id) {
		repositorio.deleteById(id);

	}
}
