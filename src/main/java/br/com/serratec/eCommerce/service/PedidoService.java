package br.com.serratec.eCommerce.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.eCommerce.exception.ResourceBadRequestException;
import br.com.serratec.eCommerce.exception.ResourceNotFoundException;
import br.com.serratec.eCommerce.model.Cliente;
import br.com.serratec.eCommerce.model.ItemPedido;
import br.com.serratec.eCommerce.model.MensagemEmail;
import br.com.serratec.eCommerce.model.Pedido;
import br.com.serratec.eCommerce.model.Produto;
import br.com.serratec.eCommerce.repository.ClienteRepository;
import br.com.serratec.eCommerce.repository.ItemPedidoRepository;
import br.com.serratec.eCommerce.repository.PedidoRepository;
import br.com.serratec.eCommerce.utils.ConversorDeData;


@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repositorio;
	
	@Autowired
	private ClienteRepository clienteRepositorio;
	
	
	@Autowired
	private EmailService emailService;
	
	
	public List<Pedido> obterTodos(){
		return repositorio.findAll();
	}
	
	public Optional<Pedido> obterPorId(Long id){
		Optional<Pedido> optPedido = repositorio.findById(id);
		
		if(optPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível encontrar a pedido com id " + id);
		}
		
		return optPedido;
	}
	
	public Pedido cadastrar(Pedido pedido) {
		validarModelo(pedido);

		
		var destinatarios = new ArrayList<String>();
		destinatarios.add("hectoroliveira1@gmail.com");
		
		Optional <Cliente> cliente = clienteRepositorio.findById(pedido.getCliente().getId());
		String dataPedido = ConversorDeData.converterDateParaData(pedido.getDataPedido());
		String dataEnvio = ConversorDeData.converterDateParaData(pedido.getDataEnvio());
		String dataEntrega = ConversorDeData.converterDateParaData(pedido.getDataEntrega());
		String mensagem = "<h1 style=\"color:blue\",\"font-size:30px\">  Novo pedido feito pelo cliente: " + cliente.get().getNomeCompleto() +" </h1> "
				+"<h2>Dados do cliente:</h2>"
				+"<ul><li>E-mail:"+cliente.get().getEmail()+"</li>"
				+"<li>Nome de usuário:"+cliente.get().getNomeUsuario()+"</li>"
				+"<li>CPF:"+cliente.get().getCpf()+"</li>"
				+"<li>Telefone:"+cliente.get().getTelefone()+"</li></ul>"
				+"<h2>Dados do pedido:</h2>"
				+"<ul><li>Status:"+pedido.getStatus()+"</li>"
				+"<li>Data do pedido:"+dataPedido+"</li>"
				+"<li>Data de envio:"+dataEnvio+"</li>"
				+"<li>Data de entrega:"+dataEntrega+"</li>";
		
		MensagemEmail email = new MensagemEmail(
				"Novo pedido criado.",
				mensagem, 
				"hectoroliveira1@gmail.com",
				destinatarios);
		
		emailService.enviar(email);
		pedido.setId(null);
		return repositorio.save(pedido);
	}
	
	public Pedido atualizar(Long id, Pedido pedido) {
		
		obterPorId(id);
		
		validarModelo(pedido);
		
		pedido.setId(id);
		return repositorio.save(pedido);
	}
	public void deletar(Long id) {
		obterPorId(id);
		
		repositorio.deleteById(id);
	}
	
	private void validarModelo(Pedido pedido) {
		
		Calendar dAtual = Calendar.getInstance();
		Calendar dPedido = Calendar.getInstance();
		Date dataAtual = new Date();
		Date dataPedido = pedido.getDataPedido();
		
		dAtual.setTime(dataAtual);
		dPedido.setTime(dataPedido);
		
		if(dPedido.after(dAtual)==false ) {
			throw new ResourceBadRequestException("A data do pedido não pode ser inferior a data atual");
		}	
	}
	
	
	
	
	}
