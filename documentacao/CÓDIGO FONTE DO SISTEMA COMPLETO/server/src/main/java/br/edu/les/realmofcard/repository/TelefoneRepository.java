package br.edu.les.realmofcard.repository;

import br.edu.les.realmofcard.domain.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
    List<Telefone> findByPessoa_Id(Integer id);
}
