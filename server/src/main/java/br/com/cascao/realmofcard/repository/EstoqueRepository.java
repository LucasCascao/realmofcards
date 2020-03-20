package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Estoque;
import org.springframework.data.repository.CrudRepository;

public interface EstoqueRepository extends CrudRepository<Estoque, Integer> {
}
