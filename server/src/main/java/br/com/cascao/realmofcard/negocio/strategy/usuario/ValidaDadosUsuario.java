package br.com.cascao.realmofcard.negocio.strategy.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validator.StringValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosUsuario implements IStrategy {

    @Autowired
    StringValidador stringValidador;

    @Override
    public String processar(EntidadeDominio entidade) {

        Pessoa pessoa = (Pessoa) entidade;

        StringBuilder msg = new StringBuilder();

        msg.append(stringValidador.validar(pessoa.getUsuario().getEmail(), "email"));

        if(pessoa.getId() == null){
            msg.append(stringValidador.validar(pessoa.getUsuario().getPassword(), "senha"));
        }

        return msg.toString();
    }
}
