package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findUsuarioById(Integer id);

    Usuario findByEmail(String email);

    Boolean existsByEmailAndPassword(String email, String senha);

    Boolean existsByEmail(String email);

}
