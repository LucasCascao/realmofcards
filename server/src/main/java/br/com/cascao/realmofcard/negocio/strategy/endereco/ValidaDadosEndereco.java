package br.com.cascao.realmofcard.negocio.strategy.endereco;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosEndereco implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        return null;
    }
}
