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
public class CartaoCreditoDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private String numero;

    private Bandeira bandeira;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof CartaoCredito){

            CartaoCredito cartaoCredito = (CartaoCredito) dominio;
            CartaoCreditoDTO cartaoCreditoDTO = new CartaoCreditoDTO();

            cartaoCreditoDTO.setId(cartaoCredito.getId());
            cartaoCreditoDTO.setNumero(cartaoCredito.getNumero()
                    .substring(cartaoCredito.getNumero().length()-4));
            cartaoCreditoDTO.setBandeira(cartaoCredito.getBandeira());

            return cartaoCreditoDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
