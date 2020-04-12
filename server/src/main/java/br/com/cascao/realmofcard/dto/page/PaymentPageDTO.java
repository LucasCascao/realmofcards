package br.com.cascao.realmofcard.dto.page;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.dto.CarrinhoDTO;
import br.com.cascao.realmofcard.dto.CartaoCreditoDTO;
import br.com.cascao.realmofcard.dto.EnderecoDTO;
import br.com.cascao.realmofcard.dto.IDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Component
public class PaymentPageDTO extends EntidadeDominio implements IDTO {

    private EnderecoDTO endereco;

    private List<CartaoCreditoDTO> cartaoCreditoList;

    private CarrinhoDTO carrinho;

    private Pessoa pessoa;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
