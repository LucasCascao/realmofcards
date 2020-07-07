package br.edu.les.realmofcard.strategy.usuario;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.repository.UsuarioRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaUsuarioAtivo implements IStrategy {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Pessoa || entidade instanceof Usuario){

            Usuario usuarioRecebido = null;

            if(entidade instanceof Pessoa) usuarioRecebido = ((Pessoa) entidade).getUsuario();

            if(entidade instanceof Usuario) usuarioRecebido = (Usuario) entidade;

            Usuario usuarioValidador = usuarioRepository.findByEmail(usuarioRecebido.getEmail());

            if(usuarioValidador != null && usuarioValidador.getStatus().getId() == 2){
                msg.append("Conta inativada.");
            }
        }

        return msg.toString();
    }
}
