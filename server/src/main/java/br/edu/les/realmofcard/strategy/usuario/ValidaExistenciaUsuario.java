package br.edu.les.realmofcard.strategy.usuario;

import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.pessoa.ValidaExistenciaPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.repository.UsuarioRepository;

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
