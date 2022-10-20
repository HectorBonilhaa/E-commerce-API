package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.model.Pedido;
import br.com.serratec.eCommerce.repository.PedidoRepository;

@Service
public class PedidoService {

	private PedidoRepository repositorio;

	public List<Pedido> listarTodos() {
		return repositorio.findAll();
	}

	public Optional<Pedido> procurarId(Long id) {
		return repositorio.findById(id);
	}

	public Pedido cadastrar(Pedido pedido) {

		pedido.setId(null);
		return repositorio.save(pedido);

	}

	public Pedido atualizar(Long id, Pedido pedido) {

		pedido.setId(id);
		return repositorio.save(pedido);

	}

	public void deletar(Long id) {
		repositorio.deleteById(id);

	}
}
