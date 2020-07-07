package br.edu.les.realmofcard.repository;

import br.edu.les.realmofcard.domain.GrupoPrecificacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoPrecificacaoRepository extends CrudRepository<GrupoPrecificacao, Integer> {
}
