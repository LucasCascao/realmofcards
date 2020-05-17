package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.domain.Pedido;

import java.util.List;
import java.util.Set;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

   List<Pedido> findByCliente_Id(Integer id);

   Set<Pedido> findByStatusPedido_Id(Integer id);

}
