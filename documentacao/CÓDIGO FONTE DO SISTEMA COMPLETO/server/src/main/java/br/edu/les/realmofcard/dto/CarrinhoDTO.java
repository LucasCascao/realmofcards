package br.edu.les.realmofcard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Component
public class CarrinhoDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private List<Item> itemList;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof Carrinho){

            CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
            Carrinho carrinho = (Carrinho) dominio;

            carrinhoDTO.setId(carrinho.getId());
            carrinhoDTO.setItemList(carrinho.getItemList());

            return carrinhoDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
