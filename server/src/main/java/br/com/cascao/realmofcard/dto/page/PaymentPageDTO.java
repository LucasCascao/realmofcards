package br.com.cascao.realmofcard.dto.page;

import br.com.cascao.realmofcard.domain.*;
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

    Endereco endereco;

    CartaoCredito cartaoCredito1;

    CartaoCredito cartaoCredito2;

    @Override
    public EntidadeDominio getDTO(EntidadeDominio dominio) {

        if(dominio instanceof PaymentPageDTO){
            PaymentPageDTO paymentPageDTO = new PaymentPageDTO();



            return PaymentPageDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio getEntidade(IDTO dto) {
        return null;
    }
}
