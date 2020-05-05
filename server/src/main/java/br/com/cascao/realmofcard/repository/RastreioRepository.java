package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Bandeira;
import br.com.cascao.realmofcard.domain.Rastreio;
import org.springframework.data.repository.CrudRepository;

public interface RastreioRepository extends CrudRepository<Rastreio, Integer> {
    Rastreio findByTroca_Id(Integer id);
}
