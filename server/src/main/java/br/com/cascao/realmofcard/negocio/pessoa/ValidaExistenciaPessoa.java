package br.com.cascao.realmofcard.negocio.pessoa;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidaExistenciaPessoa implements IStrategy {


    PessoaRepository pessoaRepository;

    @Autowired
    ValidaExistenciaPessoa(PessoaRepository pessoaDAO){
        this.pessoaRepository = pessoaDAO;
    }

    @Override
    public String processar(EntidadeDominio entidade) {
        Pessoa pessoa = (Pessoa) entidade;
        StringBuilder msg = new StringBuilder();
        if(pessoa.getCpf() != null){
            if(pessoaRepository.existsPessoaByCpf(pessoa.getCpf())){
                msg.append("CPF já cadastrado.");
            }
        }
        return msg.toString();
    }
}
