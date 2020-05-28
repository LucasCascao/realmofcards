package br.edu.les.realmofcard.strategy.usuario;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.util.validador.ValidadorString;

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
