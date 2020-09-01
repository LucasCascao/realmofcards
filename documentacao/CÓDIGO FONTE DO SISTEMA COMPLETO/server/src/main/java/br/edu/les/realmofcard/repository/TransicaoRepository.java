package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Transicao;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TransicaoRepository extends CrudRepository<Transicao, Integer> {

    List<Transicao> findByPedido_StatusPedido_Id(Integer id);

    @Query("select t FROM Transicao t WHERE t.pedido.statusPedido.id = :idStatusPedido and t.statusTransacao.id = :idStatusTroca and t.tipoTransicao.id = :idTipoTransicao")
    Set<Transicao> getTrocasByStatusAndPedidoStatus(@Param("idStatusTroca") Integer idStatusTroca,
                                                    @Param("idStatusPedido") Integer idStatusPedido,
                                                    @Param("idTipoTransicao") Integer idTipoTransicao);

    @Query("select t FROM Transicao t WHERE (t.pedido.statusPedido.id = 10 or t.pedido.statusPedido.id = 13) and t.statusTransacao.id = 1")
    Set<Transicao> getTransacoesAprovadas();

    @Query("select t FROM Transicao t WHERE t.pedido.statusPedido.id = 9")
    Set<Transicao> getTrocasProdutoPendenteRecebimento();
}
