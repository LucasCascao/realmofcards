package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Status;
import br.com.cascao.realmofcard.domain.StatusPedido;
import org.springframework.data.repository.CrudRepository;

public interface StatusPedidoRepository extends CrudRepository<StatusPedido, Integer> {

}
