package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.domain.Pedido;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

   List<Pedido> findByCliente_Id(Integer id);

   Set<Pedido> findByStatusPedido_Id(Integer id);

   @Query("SELECT p FROM Pedido p WHERE p.dataCompra >= :inicio AND p.dataCompra <= :fim order by p.dataCompra")
   List<Pedido> getDataToSellDashboard(LocalDate inicio, LocalDate fim);

}
