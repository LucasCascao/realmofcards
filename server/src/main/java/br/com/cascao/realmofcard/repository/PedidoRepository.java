package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
}
