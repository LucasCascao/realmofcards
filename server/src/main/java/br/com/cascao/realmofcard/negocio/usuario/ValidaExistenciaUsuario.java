package br.com.cascao.realmofcard.negocio.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.repository.PessoaRepository;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ValidaExistenciaUsuario implements IStrategy {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        Usuario usuario = (Usuario) entidade;
        StringBuilder msg = new StringBuilder();
        if(usuarioRepository.existsByEmail(usuario.getEmail()) == null){
            msg.append("Email já cadastrado.");
        }
        if(usuarioRepository.existsByUsername(usuario.getUsername()) == null){
            msg.append("Email já cadastrado.");
        }
        return msg.toString();
    }
}
