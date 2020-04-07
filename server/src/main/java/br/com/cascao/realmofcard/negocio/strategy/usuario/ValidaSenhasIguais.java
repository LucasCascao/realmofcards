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

        if (entidade instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) entidade;
            Usuario usuario = pessoa.getUsuario();
            if(Util.isNotNull(usuario.getPassword()) && Util.isNotNull(usuario.getRePassword())) {
                if(Util.isEquals(usuario.getPassword(), usuario.getRePassword())) {
                    msg.append("Senhas estão diferentes.");
                }
            }
        }
        return msg.toString();
    }
}
