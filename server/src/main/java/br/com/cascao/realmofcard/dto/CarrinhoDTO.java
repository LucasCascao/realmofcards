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
public class CarrinhoDTO extends EntidadeDominio {

    private Integer id;

    private List<Item> itens;

    public CarrinhoDTO tranfereParaCarrinhoDTO(Carrinho carrinho){

        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();

        carrinhoDTO.setId(carrinho.getId());
        carrinhoDTO.setItens(carrinho.getItens());

        return carrinhoDTO;
    }
}
