package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Troca;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrocaRepository extends CrudRepository<Troca, Integer> {
    List<Troca> findByPedidoParaTroca_StatusPedido_Id(Integer id);
}
