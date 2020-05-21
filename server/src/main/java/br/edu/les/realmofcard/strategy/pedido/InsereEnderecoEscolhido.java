package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Endereco;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.util.validador.ValidadorString;

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
