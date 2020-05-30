package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.les.realmofcard.domain.Cupom;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CupomRepository extends CrudRepository<Cupom, Integer> {
    List<Cupom> findByStatus_Id(Integer id);
    List<Cupom> findByPessoa_Id(Integer id);
    Cupom findCupomByCodigo(String codigo);
}
