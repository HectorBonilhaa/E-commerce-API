package br.com.serratec.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.eCommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
