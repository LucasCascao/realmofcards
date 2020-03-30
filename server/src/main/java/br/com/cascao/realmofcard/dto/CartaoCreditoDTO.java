package br.com.cascao.realmofcard.dto;

import br.com.cascao.realmofcard.domain.Bandeira;
import br.com.cascao.realmofcard.domain.CartaoCredito;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Setter
@Getter
@Component
public class CartaoCreditoDTO extends EntidadeDominio {

    private Integer id;

    private String numero;

    private Bandeira bandeira;

    public CartaoCreditoDTO transfereParaCartaoCreditoDTO(CartaoCredito cartaoCredito){

        CartaoCreditoDTO cartaoCreditoDTO = new CartaoCreditoDTO();

        cartaoCreditoDTO.setId(cartaoCredito.getId());

        cartaoCreditoDTO.setNumero(cartaoCredito.getNumero()
                .substring(cartaoCredito.getNumero().length()-4));


        cartaoCreditoDTO.setBandeira(cartaoCredito.getBandeira());

        return cartaoCreditoDTO;
    }
}
