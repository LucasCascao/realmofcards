package br.edu.les.realmofcard.strategy.usuario;

import br.edu.les.realmofcard.domain.Usuario;
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

        if(entidade instanceof Pessoa || entidade instanceof Usuario) {
            Usuario usuario = null;
            if(entidade instanceof Pessoa) usuario = ((Pessoa) entidade).getUsuario();
            if(entidade instanceof Usuario) usuario = (Usuario) entidade;

            if(validadorString.validar(usuario.getPassword(), "senha") == ""){
                usuario.setPassword(Criptografia.criptografar(usuario.getPassword()));
            }
        }

        return null;
    }
}
