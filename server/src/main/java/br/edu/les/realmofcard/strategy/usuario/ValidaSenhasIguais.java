package br.edu.les.realmofcard.strategy.usuario;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.util.Util;

@Component
public class ValidaSenhasIguais implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if (entidade instanceof Pessoa
            || entidade instanceof Usuario) {

            Usuario usuario = null;

            if(entidade instanceof Pessoa) usuario = ((Pessoa) entidade).getUsuario();

            if(entidade instanceof Usuario) usuario = (Usuario) entidade;

            if(Util.isNotNull(usuario.getPassword()) && Util.isNotNull(usuario.getRePassword())) {
                if(!usuario.getPassword().equals(usuario.getRePassword())) {
                    msg.append("Senhas estão diferentes.");
                }
            }
        }
        return msg.toString();
    }
}
