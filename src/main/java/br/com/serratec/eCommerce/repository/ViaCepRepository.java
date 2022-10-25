package br.com.serratec.eCommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.eCommerce.dto.ProdutoRequestDTO;
import br.com.serratec.eCommerce.model.Produto;

@Repository
public interface ViaCepRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findByNome(String nome);

	public void saveAndFlush(ProdutoRequestDTO produto);
}
