package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Carta;

import java.util.List;

public interface CartaRepository extends CrudRepository<Carta, Integer> {

    List<Carta> findByStatus_Id(Integer id);

    Boolean existsByImagemPath(String imagemPath);
}
