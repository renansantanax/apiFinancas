package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.collections.LogFinancas;

@Repository
public interface LogFinancasRepository extends MongoRepository<LogFinancas, UUID> {

}
