package br.edu.les.realmofcard.dto;

import br.edu.les.realmofcard.domain.Telefone;
import br.edu.les.realmofcard.util.Util;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private List<TelefoneDTO> telefones;

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
            if(Util.isNotNull(pessoa.getTelefones())){
                List<TelefoneDTO> telefones = new ArrayList<>();
                pessoa.getTelefones().forEach(telefone -> telefones.add((TelefoneDTO) new TelefoneDTO().parseEntityToDTO(telefone)));
                pessoaDTO.setTelefones(telefones);
            }

            if(Util.isNotNull(pessoa.getUsuario())
                && Util.isNotNull(pessoa.getUsuario().getId()) != null)
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
