package br.com.serratec.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.eCommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
