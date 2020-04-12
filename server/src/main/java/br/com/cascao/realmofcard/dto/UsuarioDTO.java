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

    private Boolean isAdmin;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof Usuario){

            Usuario usuario = (Usuario) dominio;
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setEmail(usuario.getEmail());

            if(usuario.getTipoUsuario().getTipo().trim().toLowerCase().equals("administrador")) usuarioDTO.setIsAdmin(true);
            else usuarioDTO.setIsAdmin(false);

            return usuarioDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
