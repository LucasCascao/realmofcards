package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Status;
import br.edu.les.realmofcard.domain.StatusPedido;

public interface StatusPedidoRepository extends CrudRepository<StatusPedido, Integer> {

}
