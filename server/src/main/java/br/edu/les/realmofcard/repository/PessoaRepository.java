package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.edu.les.realmofcard.domain.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

    boolean existsPessoaByCpf(String id);

    Pessoa findPessoaByUsuario_Id(Integer id);

    Pessoa findByUsuario_Email(String email);

}
