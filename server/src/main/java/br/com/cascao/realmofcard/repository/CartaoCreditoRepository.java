package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.CartaoCredito;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito, Integer> {

    List<CartaoCredito> findByPessoa_Id(Integer id);

    List<CartaoCredito> findByPessoa_IdAndPreferido(Integer id, Boolean preferido);

    Boolean existsByNumero(String numero);
}
