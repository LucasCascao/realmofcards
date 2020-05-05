package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Cupom;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CupomRepository extends CrudRepository<Cupom, Integer> {
    List<Cupom> findByStatus_Id(Integer id);
    List<Cupom> findByTroca_PedidoParaTroca_Cliente_Id(Integer id);
}
