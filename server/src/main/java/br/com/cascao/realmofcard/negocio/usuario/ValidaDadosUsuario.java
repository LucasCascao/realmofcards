package br.com.cascao.realmofcard.negocio.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.negocio.pessoa.ValidaDadosPessoa;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import br.com.cascao.realmofcard.validator.StringValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidaDadosUsuario implements IStrategy {

    @Autowired
    ValidaDadosPessoa validaDadosPessoa;

    @Autowired
    StringValidador stringValidador;

    @Override
    public String processar(EntidadeDominio entidade) {

        Usuario usuario = (Usuario) entidade;

        StringBuilder msg = new StringBuilder();

        msg.append(validaDadosPessoa.processar(usuario.getPessoa()));
        msg.append(stringValidador.validar(usuario.getEmail(), "email"));
        msg.append(stringValidador.validar(usuario.getUsername(), "nome de usuario"));
        msg.append(stringValidador.validar(usuario.getPassword(), "senha"));


        return msg.toString();
    }
}
