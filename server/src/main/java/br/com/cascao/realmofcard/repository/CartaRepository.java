package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Carta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartaRepository extends CrudRepository<Carta, Integer> {
    List<Carta> findByStatus_Id(Integer id);
    Boolean existsByImagemPath(String imagemPath);
}
