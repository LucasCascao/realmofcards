package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Endereco;

import java.util.List;

public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {

    List<Endereco> findByPessoa_Id(Integer id);

    Endereco findByPessoa_IdAndPreferido(Integer id, Boolean preferido);
}
