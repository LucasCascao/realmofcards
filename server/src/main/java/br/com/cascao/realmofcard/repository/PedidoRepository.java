package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.domain.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

   Pedido findByItens(Item item);

   List<Pedido> findByCliente_Id(Integer id);

}
