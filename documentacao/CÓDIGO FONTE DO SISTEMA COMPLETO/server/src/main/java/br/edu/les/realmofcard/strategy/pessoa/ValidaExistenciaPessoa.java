package br.edu.les.realmofcard.strategy.pessoa;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.repository.PessoaRepository;

@Service
public class ValidaExistenciaPessoa implements IStrategy {

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public String processar(final EntidadeDominio entidade) {
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
