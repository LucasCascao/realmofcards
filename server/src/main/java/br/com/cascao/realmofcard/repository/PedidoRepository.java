package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.domain.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

   List<Pedido> findByCliente_Id(Integer id);

   Set<Pedido> findByStatusPedido_Id(Integer id);

}
