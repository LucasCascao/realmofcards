package br.com.cascao.realmofcard.negocio.strategy.endereco;

import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validator.StringValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosEndereco implements IStrategy {

    @Autowired
    StringValidador stringValidador;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Endereco){
            Endereco endereco = (Endereco) entidade;
            msg.append(stringValidador.validar(endereco.getLogradouro(),"logradouro"));
            msg.append(stringValidador.validar(endereco.getNumero(), "numero"));
            msg.append(stringValidador.validar(endereco.getBairro(), "bairro"));
            msg.append(stringValidador.validar(endereco.getCep(), "cep"));
            if(endereco.getCidade() != null) {
                msg.append(stringValidador.validar(endereco.getCidade().getNome(), "cidade"));
                if(endereco.getCidade().getEstado() != null){
                    msg.append(stringValidador.validar(endereco.getCidade().getEstado().getNome(), "estado"));
                } else msg.append(stringValidador.validar("", "estado"));
            } else {
                msg.append(stringValidador.validar("", "cidade"));
                msg.append(stringValidador.validar("", "estado"));
            }
        }

        return msg.toString();
    }
}
