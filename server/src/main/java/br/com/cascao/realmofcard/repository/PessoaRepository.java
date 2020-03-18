package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Integer> {

    boolean existsPessoaByCpf(String id);

    Pessoa findPessoaByUsuario_Id(Integer id);

}
