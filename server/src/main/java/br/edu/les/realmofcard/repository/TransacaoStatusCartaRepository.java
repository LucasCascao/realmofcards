package br.edu.les.realmofcard.repository;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.TransacaoStatusCarta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoStatusCartaRepository extends CrudRepository<TransacaoStatusCarta, Integer> {
    List<TransacaoStatusCarta> findByCarta_Id_OrderByCarta_Id(Integer cartaId);
}
