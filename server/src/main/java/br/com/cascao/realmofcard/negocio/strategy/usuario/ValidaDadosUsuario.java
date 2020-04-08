package br.com.cascao.realmofcard.negocio.strategy.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosUsuario implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if (entidade instanceof Pessoa || entidade instanceof Usuario) {

            Usuario usuario;

            if(entidade instanceof Pessoa){
                Pessoa pessoa = (Pessoa) entidade;
                usuario = pessoa.getUsuario();
            } else {
                usuario = (Usuario) entidade;
            }

            msg.append(validadorString.validar(usuario.getEmail(), "email"));

            if (usuario.getId() == null) {
                msg.append(validadorString.validar(usuario.getPassword(), "senha"));
            }
        }

        return msg.toString();
    }
}
