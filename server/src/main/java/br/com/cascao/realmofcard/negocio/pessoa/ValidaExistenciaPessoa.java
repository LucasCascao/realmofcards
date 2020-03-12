package br.com.cascao.realmofcard.negocio.pessoa;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.repository.PessoaDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidaExistenciaPessoa implements IStrategy {

    @Autowired
    PessoaDAO pessoaDAO;

    @Override
    public String processar(EntidadeDominio entidade) {
        Usuario usuario = (Usuario) entidade;
        StringBuilder msg = new StringBuilder();
//        if(pessoaDAO.existsPessoaByCpf(usuario.getId())){
//            msg.append("CPF já cadastrado.");
//        }
//
//        if(pessoaDAO.existsPessoaByEmail(usuario.getEmail())){
//            msg.append("Email já cadastrado");
//        }
        return msg.toString();
    }
}
