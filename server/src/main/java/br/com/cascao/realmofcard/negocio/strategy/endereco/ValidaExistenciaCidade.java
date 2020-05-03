package br.com.cascao.realmofcard.negocio.strategy.endereco;

import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CidadeRepository;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
