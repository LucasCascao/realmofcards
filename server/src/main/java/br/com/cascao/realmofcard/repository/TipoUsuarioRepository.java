package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
}
