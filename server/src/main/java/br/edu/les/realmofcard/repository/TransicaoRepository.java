package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Transicao;

import java.util.List;
import java.util.Set;

public interface TransicaoRepository extends CrudRepository<Transicao, Integer> {

    List<Transicao> findByPedido_StatusPedido_Id(Integer id);

    @Query("select t FROM Transicao t WHERE t.pedido.statusPedido.id = 10")
    Set<Transicao> getTrocasAprovadas();

    @Query("select t FROM Transicao t WHERE t.pedido.statusPedido.id = 9")
    Set<Transicao> getTrocasProdutoPendenteRecebimento();
}
