package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
}
