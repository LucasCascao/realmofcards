package br.edu.les.realmofcard.repository;

import br.edu.les.realmofcard.domain.TipoTelefone;
import br.edu.les.realmofcard.domain.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Integer> {}
