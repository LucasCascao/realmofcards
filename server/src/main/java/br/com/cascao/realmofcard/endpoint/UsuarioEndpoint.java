package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.dto.UsuarioDTO;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import br.com.cascao.realmofcard.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private Resultado resultado;

    @Autowired
    private UsuarioDTO usuarioDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.consultar(usuario), usuarioDTO));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.alterar(usuario), usuarioDTO));
    }
}
