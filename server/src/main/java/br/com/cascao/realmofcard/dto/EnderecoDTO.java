package br.com.cascao.realmofcard.dto;

import br.com.cascao.realmofcard.domain.Cidade;
import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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

    private Boolean preferido;

    private Cidade cidade;

    @Override
    public EntidadeDominio getDTO(EntidadeDominio dominio) {

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
            enderecoDTO.setPreferido(endereco.getPreferido());

            return enderecoDTO;
        }

        return null;
    }

    @Override
    public EntidadeDominio getEntidade(IDTO dto) {
        return null;
    }
}
