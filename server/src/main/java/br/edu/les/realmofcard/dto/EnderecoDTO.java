package br.edu.les.realmofcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Cidade;
import br.edu.les.realmofcard.domain.Endereco;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class EnderecoDTO extends EntidadeDominio implements IDTO {

    private Integer id;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cep;

    private String complemento;

    private Cidade cidade;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof Endereco){

            EnderecoDTO enderecoDTO = new EnderecoDTO();
            Endereco endereco = (Endereco) dominio;

            enderecoDTO.setId(endereco.getId());
            enderecoDTO.setLogradouro(endereco.getLogradouro());
            enderecoDTO.setNumero(endereco.getNumero());
            enderecoDTO.setBairro(endereco.getBairro());
            enderecoDTO.setCep(endereco.getCep());
            enderecoDTO.setComplemento(endereco.getComplemento());
            enderecoDTO.setCidade(endereco.getCidade());

            return enderecoDTO;
        }

        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
