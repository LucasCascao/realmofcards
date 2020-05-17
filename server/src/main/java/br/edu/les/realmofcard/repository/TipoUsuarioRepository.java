package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.TipoUsuario;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {

    TipoUsuario findTipoUsuarioById(Integer id);
}
