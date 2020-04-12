package br.com.cascao.realmofcard.dto;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import br.com.cascao.realmofcard.domain.Item;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Component
public class CarrinhoDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private List<Item> itens;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof Carrinho){

            CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
            Carrinho carrinho = (Carrinho) dominio;

            carrinhoDTO.setId(carrinho.getId());
            carrinhoDTO.setItens(carrinho.getItens());

            return carrinhoDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
