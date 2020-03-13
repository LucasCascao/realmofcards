package br.com.cascao.realmofcard.negocio.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosUsuario implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        Usuario usuario = (Usuario) entidade;
        StringBuilder msg = new StringBuilder();
        if(usuario.getEmail() == null || usuario.getEmail().trim().equals("")){
            msg.append("Campo email é obrigatório.");
        }
        if(usuario.getUsername() == null || usuario.getUsername().trim().equals("")){
            msg.append("Campo username é obrigatório.");
        }
        if(usuario.getPassword() == null || usuario.getPassword().trim().equals("")){
            msg.append("Campo password é obrigatório.");
        }
        return msg.toString();
    }
}
