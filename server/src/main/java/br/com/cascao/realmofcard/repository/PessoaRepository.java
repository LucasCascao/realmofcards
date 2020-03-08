package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Pessoa;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
