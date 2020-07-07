package br.edu.les.realmofcard.dto;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Telefone;
import br.edu.les.realmofcard.domain.TipoTelefone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Component
public class TelefoneDTO extends EntidadeDominio implements IDTO{


    private Integer id;

    private String ddd;

    private String numero;

    private TipoTelefone tipoTelefone;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {
        if(dominio instanceof Telefone){
            TelefoneDTO telefoneDTO = new TelefoneDTO();
            Telefone telefone = (Telefone) dominio;

            telefoneDTO.setId(telefone.getId());
            telefoneDTO.setDdd(telefone.getDdd());
            telefoneDTO.setNumero(telefone.getNumero());
            telefoneDTO.setTipoTelefone(telefone.getTipoTelefone());

            return telefoneDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
