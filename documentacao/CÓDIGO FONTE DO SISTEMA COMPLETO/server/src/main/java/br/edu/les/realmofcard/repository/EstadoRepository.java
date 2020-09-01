package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.CartaoCredito;
import br.edu.les.realmofcard.domain.Estado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {}
