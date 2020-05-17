package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Troca;

import java.util.List;
import java.util.Set;

public interface TrocaRepository extends CrudRepository<Troca, Integer> {

    List<Troca> findByPedidoParaTroca_StatusPedido_Id(Integer id);

    @Query("select t FROM Troca t WHERE t.pedidoParaTroca.statusPedido.id = 10")
    Set<Troca> getTrocasAprovadas();

    @Query("select t FROM Troca t WHERE t.pedidoParaTroca.statusPedido.id = 9")
    Set<Troca> getTrocasProdutoPendenteRecebimento();
}
