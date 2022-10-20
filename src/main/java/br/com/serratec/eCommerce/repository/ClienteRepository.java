package br.com.serratec.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.eCommerce.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
