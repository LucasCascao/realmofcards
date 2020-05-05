package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Troca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface TrocaRepository extends CrudRepository<Troca, Integer> {

    List<Troca> findByPedidoParaTroca_StatusPedido_Id(Integer id);

    @Query("select t FROM Troca t WHERE t.pedidoParaTroca.statusPedido.id = 10")
    Set<Troca> getTrocasAprovadas();

    @Query("select t FROM Troca t WHERE t.pedidoParaTroca.statusPedido.id = 9")
    Set<Troca> getTrocasProdutoPendenteRecebimento();
}
