package br.com.serratec.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.eCommerce.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
