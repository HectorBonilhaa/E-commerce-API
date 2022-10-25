package br.com.serratec.eCommerce.dto;

import java.util.Date;
import java.util.List;



public class PedidoRequestDTO {
	
	private Date dataPedido;

	private Date dataEntrega;
	
	private Date dataEnvio;
	
	private String status;
	
	private ClienteRequestDTO cliente;
	
	private List<ItemPedidoRequestDTO> itensPedidos;

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ClienteRequestDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteRequestDTO cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoRequestDTO> getItensPedidos() {
		return itensPedidos;
	}

	public void setItensPedidos(List<ItemPedidoRequestDTO> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}
	
	
}
