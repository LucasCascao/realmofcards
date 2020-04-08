package br.com.cascao.realmofcard.dto;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Status;
import br.com.cascao.realmofcard.domain.TipoUsuario;
import br.com.cascao.realmofcard.domain.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Setter
@Getter
@Component
public class UsuarioDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private String email;

    @Override
    public EntidadeDominio getDTO(EntidadeDominio dominio) {

        if(dominio instanceof Usuario){

            Usuario usuario = (Usuario) dominio;
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setEmail(usuario.getEmail());

            return usuarioDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio getEntidade(IDTO dto) {
        return null;
    }
}
