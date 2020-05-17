package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.CartaoCredito;
import br.edu.les.realmofcard.domain.Estado;

import java.util.List;

public interface EstadoRepository extends CrudRepository<Estado, Integer> {}
