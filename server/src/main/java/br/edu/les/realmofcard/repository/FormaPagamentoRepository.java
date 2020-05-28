package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.FormaPagamento;

public interface FormaPagamentoRepository extends CrudRepository<FormaPagamento, Integer> { }
