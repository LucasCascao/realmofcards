package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Endereco;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {

    List<Endereco> findByPessoa_Id(Integer id);

    @Query("SELECT e FROM Endereco e WHERE e.pessoa.id = :idPessoa AND e.status.id = :idStatus")
    List<Endereco> findEnderecoByPessoaAndStatus(@Param("idPessoa") Integer idPessoa, @Param("idStatus") Integer idStatus);
}
