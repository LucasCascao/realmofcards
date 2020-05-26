package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.CartaoCredito;

import java.util.List;

public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito, Integer> {

    List<CartaoCredito> findByPessoa_Id(Integer id);

    Boolean existsByNumero(String numero);
}
