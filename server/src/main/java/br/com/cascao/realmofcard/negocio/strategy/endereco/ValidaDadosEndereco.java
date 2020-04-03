package br.com.cascao.realmofcard.negocio.strategy.endereco;

import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosEndereco implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Endereco){
            Endereco endereco = (Endereco) entidade;
            msg.append(validadorString.validar(endereco.getLogradouro(),"logradouro"));
            msg.append(validadorString.validar(endereco.getNumero(), "numero"));
            msg.append(validadorString.validar(endereco.getBairro(), "bairro"));
            msg.append(validadorString.validar(endereco.getCep(), "cep"));
            if(endereco.getCidade() != null) {
                msg.append(validadorString.validar(endereco.getCidade().getNome(), "cidade"));
                if(endereco.getCidade().getEstado() != null){
                    msg.append(validadorString.validar(endereco.getCidade().getEstado().getNome(), "estado"));
                } else msg.append(validadorString.validar("", "estado"));
            } else {
                msg.append(validadorString.validar("", "cidade"));
                msg.append(validadorString.validar("", "estado"));
            }
        }

        return msg.toString();
    }
}
