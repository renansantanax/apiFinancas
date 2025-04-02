package br.com.cotiinformatica.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer>{
	
}
