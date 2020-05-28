package br.edu.les.realmofcard.strategy.usuario;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.util.Criptografia;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class CriptografaSenha implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) entidade;
            if(validadorString.validar(pessoa.getUsuario().getPassword(), "senha") == "")
                pessoa.getUsuario().setPassword(Criptografia.criptografar(pessoa.getUsuario().getPassword()));
        }

        return null;
    }
}
