package br.com.serratec.eCommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.eCommerce.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findByCpf(String cpf);
	
	public List<Cliente> findByEmail(String email);
}
