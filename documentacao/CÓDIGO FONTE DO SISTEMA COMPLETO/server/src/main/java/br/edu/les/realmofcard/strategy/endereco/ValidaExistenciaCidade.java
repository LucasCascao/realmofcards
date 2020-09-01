package br.edu.les.realmofcard.strategy.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Endereco;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.repository.CidadeRepository;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class ValidaExistenciaCidade implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Autowired
    CidadeRepository cidadeRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Endereco){
            Endereco endereco = (Endereco) entidade;
            if(endereco.getCidade().getNome() != null && endereco.getCidade().getEstado().getId() != null){
                if(!cidadeRepository
                        .existsByEstado_IdAndAndNome(endereco.getCidade().getEstado().getId(), endereco.getCidade().getNome())){
                    msg.append("Cidade não existe dentro do estado de " + endereco.getCidade().getEstado().getNome());
                }
            }
        }

        return msg.toString();
    }
}
