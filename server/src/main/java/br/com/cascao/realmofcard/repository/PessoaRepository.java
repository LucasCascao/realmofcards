package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
