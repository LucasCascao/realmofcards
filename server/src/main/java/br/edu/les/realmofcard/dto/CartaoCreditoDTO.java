package br.edu.les.realmofcard.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.CartaoCredito;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;

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

            if(cartaoCredito.getId() != null){
                cartaoCreditoDTO.setId(cartaoCredito.getId());
                cartaoCreditoDTO.setNumero(cartaoCredito.getNumero()
                        .substring(cartaoCredito.getNumero().length()-4));
                cartaoCreditoDTO.setBandeira(cartaoCredito.getBandeira());

                return cartaoCreditoDTO;
            }
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
