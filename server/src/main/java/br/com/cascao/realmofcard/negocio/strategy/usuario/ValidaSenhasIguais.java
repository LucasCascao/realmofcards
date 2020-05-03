package br.com.cascao.realmofcard.negocio.strategy.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.stereotype.Component;

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
