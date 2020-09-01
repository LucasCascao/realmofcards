package br.edu.les.realmofcard.strategy.usuario;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Criptografia;
import br.edu.les.realmofcard.util.GeradorCodigo;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoUsuario implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pessoa || entidade instanceof Usuario) {
            Usuario usuario = null;
            if(entidade instanceof Pessoa && Util.isNotNull(((Pessoa) entidade).getUsuario())) usuario = ((Pessoa) entidade).getUsuario();
            else return null;
            if(entidade instanceof Usuario) usuario = (Usuario) entidade;

            usuario.setCodigo(GeradorCodigo.gerarCodigoUsuario());
        }
        return null;
    }
}
