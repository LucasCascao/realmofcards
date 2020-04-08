package br.com.cascao.realmofcard.negocio.strategy.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.Criptografia;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
