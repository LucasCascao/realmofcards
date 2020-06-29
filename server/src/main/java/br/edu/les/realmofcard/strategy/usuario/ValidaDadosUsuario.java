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
        Boolean eInstanciaDePessoa = false;

        if (entidade instanceof Pessoa || entidade instanceof Usuario) {

            Usuario usuario = null;

            if(entidade instanceof Pessoa) {
                usuario = ((Pessoa) entidade).getUsuario();
                eInstanciaDePessoa = true;
            } else {
                usuario = (Usuario) entidade;
            }

            msg.append(validadorString.validar(usuario.getEmail(), "email"));
            if(eInstanciaDePessoa){
                if (usuario.getId() == null) {
                    msg.append(validadorString.validar(usuario.getPassword(), "senha"));
                    msg.append(validadorString.validar(usuario.getRePassword(), "confirmar senha"));
                }
            } else {
                if (usuario.getId() == null) {
                    msg.append(validadorString.validar(usuario.getPassword(), "senha"));
                } else {
                    msg.append(validadorString.validar(usuario.getPassword(), "senha"));
                    msg.append(validadorString.validar(usuario.getRePassword(), "confirmar senha"));
                }
            }
        }

        return msg.toString();
    }
}
