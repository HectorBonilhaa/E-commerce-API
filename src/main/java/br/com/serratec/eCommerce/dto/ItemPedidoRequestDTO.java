package br.com.serratec.eCommerce.dto;

import java.util.Date;
import java.util.List;



public class ItemPedidoRequestDTO {
	
	private Integer quantidade;
	
	private Double percentualDesconto;
	
	private Double valorBruto;
	
	private Double valorLiquido;
	
	private ProdutoRequestDTO produto;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public ProdutoRequestDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoRequestDTO produto) {
		this.produto = produto;
	}
	
	
}
