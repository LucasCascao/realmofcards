package br.com.cascao.realmofcard.dto;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Component
public class PessoaDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String sexo;

    private LocalDate dataNascimento;

    private UsuarioDTO usuario;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {
        if(dominio instanceof Pessoa){
            PessoaDTO pessoaDTO = new PessoaDTO();
            Pessoa pessoa = (Pessoa) dominio;

            pessoaDTO.setId(pessoa.getId());
            pessoaDTO.setNome(pessoa.getNome());
            pessoaDTO.setSobrenome(pessoa.getSobrenome());
            pessoaDTO.setCpf(pessoa.getCpf());
            pessoaDTO.setSexo(pessoa.getSexo());
            pessoaDTO.setDataNascimento(pessoa.getDataNascimento());
            if(pessoa.getUsuario() != null
                && pessoa.getUsuario().getId() != null)
                pessoaDTO.setUsuario((UsuarioDTO) new UsuarioDTO()
                        .parseEntityToDTO(pessoa.getUsuario()));

            return pessoaDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
