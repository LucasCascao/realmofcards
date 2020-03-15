package br.com.cascao.realmofcard.negocio.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.negocio.pessoa.ValidaExistenciaPessoa;
import br.com.cascao.realmofcard.repository.PessoaRepository;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ValidaExistenciaUsuario implements IStrategy {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ValidaExistenciaPessoa validaExistenciaPessoa;

    @Override
    public String processar(EntidadeDominio entidade) {

        Pessoa pessoa = (Pessoa) entidade;
        StringBuilder msg = new StringBuilder();

        Usuario usuarioRecebido = pessoa.getUsuario();

        Usuario usuarioValidador = usuarioRepository.findByEmail(usuarioRecebido.getEmail());

        if(usuarioValidador != null){
            if(usuarioRecebido.getId() != usuarioValidador.getId()){
                msg.append("Email já cadastrado.");
            }
        }

        return msg.toString();
    }
}
