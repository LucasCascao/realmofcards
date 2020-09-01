package br.edu.les.realmofcard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Status;
import br.edu.les.realmofcard.domain.TipoUsuario;
import br.edu.les.realmofcard.domain.Usuario;

@NoArgsConstructor
@Setter
@Getter
@Component
public class UsuarioDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private String codigo;

    private String email;

    private Boolean isAdmin;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof Usuario){

            Usuario usuario = (Usuario) dominio;
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setCodigo(usuario.getCodigo());
            usuarioDTO.setEmail(usuario.getEmail());
            
            if(usuario.getTipoUsuario() != null && usuario.getTipoUsuario().getTipo() != null
            	&& usuario.getTipoUsuario().getTipo().trim().toLowerCase().equals("administrador"))
            	usuarioDTO.setIsAdmin(true);
            else 
            	usuarioDTO.setIsAdmin(false);

            return usuarioDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
