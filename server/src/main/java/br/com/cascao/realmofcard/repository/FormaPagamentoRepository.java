package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Bandeira;
import br.com.cascao.realmofcard.domain.FormaPagamento;
import org.springframework.data.repository.CrudRepository;

public interface FormaPagamentoRepository extends CrudRepository<FormaPagamento, Integer> { }
