package br.com.serratec.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.eCommerce.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
