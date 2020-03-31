package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.CartaoCredito;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarrinhoRepository extends CrudRepository<Carrinho, Integer> {
    List<Carrinho> findByPessoa_Id(Integer id);
}
