package br.com.cascao.realmofcard.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cascao.realmofcard.domain.Pessoa;

@Repository
@Transactional
public interface PessoaDAO extends JpaRepository<Pessoa, Integer> {

//    Pessoa findByEmailAndPassword(String email, String password);

    boolean existsPessoaByCpf(Integer id);

//    boolean existsPessoaByEmail(String email);

}
