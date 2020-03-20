package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Carta;
import org.springframework.data.repository.CrudRepository;

public interface CartaRepository extends CrudRepository<Carta, Integer> {
}
