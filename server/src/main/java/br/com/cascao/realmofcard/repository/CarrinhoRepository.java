package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarrinhoRepository extends CrudRepository<Carrinho, Integer> {
//    List<Carrinho> findByPessoa_Id(Integer id);

    Carrinho findByPessoa_Id(Integer id);

    Carrinho findByItensContaining(Item item);

    void deleteByPessoa_Id(Integer id);
}
