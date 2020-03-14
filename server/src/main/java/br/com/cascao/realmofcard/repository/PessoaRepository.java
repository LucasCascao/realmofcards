package br.com.cascao.realmofcard.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cascao.realmofcard.domain.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    boolean existsPessoaByCpf(String id);
}
