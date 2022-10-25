package br.com.serratec.eCommerce.dto;

import java.util.Date;
import java.util.List;



public class PedidoResponseDTO {
	
	private Date dataPedido;

	private Date dataEntrega;
	
	private Date dataEnvio;
	
	private String status;
	
	private ClienteResponseDTO cliente;
	
	private List<ItemPedidoResponseDTO> itensPedidos;

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

	public ClienteResponseDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResponseDTO cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoResponseDTO> getItensPedidos() {
		return itensPedidos;
	}

	public void setItensPedidos(List<ItemPedidoResponseDTO> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}
	
	
}
