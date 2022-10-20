package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.model.Produto;
import br.com.serratec.eCommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private ProdutoRepository repositorio;

	public List<Produto> listarTodos() {
		return repositorio.findAll();
	}

	public Optional<Produto> procurarId(Long id) {
		return repositorio.findById(id);
	}

	public Produto cadastrar(Produto produto) {

		produto.setId(null);
		return repositorio.save(produto);

	}

	public Produto atualizar(Long id, Produto produto) {

		produto.setId(id);
		return repositorio.save(produto);

	}

	public void deletar(Long id) {
		repositorio.deleteById(id);

	}
}
