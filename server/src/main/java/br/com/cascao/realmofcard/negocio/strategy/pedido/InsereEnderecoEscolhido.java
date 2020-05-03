package br.com.cascao.realmofcard.negocio.strategy.pedido;

import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsereEnderecoEscolhido implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;

            Endereco enderecoEscolhido = pedido.getEndereco();

            pedido.setEnderecoEscolhido(
                    enderecoEscolhido.getLogradouro() + ", "
                    + "nº " + enderecoEscolhido.getNumero() + ", "
                    + "complemento " + enderecoEscolhido.getComplemento() + ", "
                    + "bairro " + enderecoEscolhido.getBairro() + ", "
                    + enderecoEscolhido.getCidade().getNome() + " - " + enderecoEscolhido.getCidade().getEstado().getSigla() + ", "
                    + "CEP " + enderecoEscolhido.getCep() + "."
            );
        }

        return msg.toString();
    }
}
