package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.CartaoCredito;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito, Integer> {

    List<CartaoCredito> findByPessoa_Id(Integer id);

    @Query("SELECT c FROM CartaoCredito c WHERE c.pessoa.id = :idPessoa AND c.status.id = :idStatus")
    List<CartaoCredito> findEnderecoByPessoaAndStatus(@Param("idPessoa") Integer idPessoa, @Param("idStatus") Integer idStatus);

    @Transactional
    @Modifying
    @Query("UPDATE CartaoCredito c SET c.status.id = 2 WHERE c.id = :idCartaoCredito")
    void inativaCartaoCredito(@Param("idCartaoCredito") Integer idCartaoCredito);

    Boolean existsByNumero(String numero);
}
