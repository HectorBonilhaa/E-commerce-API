package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.model.ItemPedido;
import br.com.serratec.eCommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	private ItemPedidoRepository repositorio;

	public List<ItemPedido> listarTodos() {
		return repositorio.findAll();
	}

	public Optional<ItemPedido> procurarId(Long id) {
		return repositorio.findById(id);
	}

	public ItemPedido cadastrar(ItemPedido itemPedido) {

		itemPedido.setId(null);
		return repositorio.save(itemPedido);

	}

	public ItemPedido atualizar(Long id, ItemPedido itemPedido) {

		itemPedido.setId(id);
		return repositorio.save(itemPedido);

	}

	public void deletar(Long id) {
		repositorio.deleteById(id);

	}
}
