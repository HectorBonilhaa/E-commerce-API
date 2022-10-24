package br.com.serratec.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.exception.ResourceBadRequestException;
import br.com.serratec.eCommerce.exception.ResourceNotFoundException;
import br.com.serratec.eCommerce.model.ItemPedido;
import br.com.serratec.eCommerce.model.Produto;
import br.com.serratec.eCommerce.repository.ItemPedidoRepository;
import br.com.serratec.eCommerce.repository.ProdutoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	private ItemPedidoRepository repositorio;

	@Autowired
	private ProdutoRepository produtoRepositorio;
	
	Produto produto = new Produto();
	
	
	public List<ItemPedido> obterTodos() {
		return repositorio.findAll();
	}

	public Optional<ItemPedido> obterPorId(Long id) {
		Optional<ItemPedido> optItemPedido = repositorio.findById(id);

		if (optItemPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível encontrar a itemPedido com id " + id);
		}

		return optItemPedido;
	}

	public ItemPedido cadastrar(ItemPedido itemPedido) {

		validarModelo(itemPedido);
		itemPedido.setValorBruto(calcularValorBruto(itemPedido));
		itemPedido.setValorLiquido(calcularValorLiquido(itemPedido));
		itemPedido.setId(null);
		return repositorio.save(itemPedido);
	}

	public ItemPedido atualizar(Long id, ItemPedido itemPedido) {

		obterPorId(id);

		validarModelo(itemPedido);
		itemPedido.setValorBruto(calcularValorBruto(itemPedido));
		itemPedido.setValorLiquido(calcularValorLiquido(itemPedido));
		itemPedido.setId(id);
		return repositorio.save(itemPedido);
	}

	public void deletar(Long id) {
		obterPorId(id);

		repositorio.deleteById(id);
	}

	private void validarModelo(ItemPedido itemPedido) {
		if (itemPedido.getQuantidade() == null) {
			throw new ResourceBadRequestException("A quantidade do produto deve ser informada");
		}
	}

	public double calcularValorBruto(ItemPedido itemPedido) {
		
		Optional <Produto> produto = produtoRepositorio.findById(itemPedido.getProduto().getId());
		Double valorBruto = produto.get().getValorUnitario() * itemPedido.getQuantidade();
		return valorBruto;
		
	}
	
	
	
	public double calcularValorLiquido(ItemPedido itemPedido) {
	Double percentual = itemPedido.getPercentualDesconto();
	if (percentual != null) {
		Double valorLiquido = (itemPedido.getValorBruto() - (itemPedido.getValorBruto() * (percentual / 100)));
		return valorLiquido;
	}
	return itemPedido.getValorBruto();

	}
}
