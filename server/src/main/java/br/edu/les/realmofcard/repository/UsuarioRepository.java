package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findUsuarioById(Integer id);

    Usuario findByEmail(String email);

    Boolean existsByEmailAndPassword(String email, String senha);

    Boolean existsByEmail(String email);

}
