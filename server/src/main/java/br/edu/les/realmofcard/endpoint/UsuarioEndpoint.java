package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.dto.PessoaDTO;
import br.edu.les.realmofcard.dto.UsuarioDTO;
import br.edu.les.realmofcard.facade.Fachada;
import br.edu.les.realmofcard.util.DTOUtil;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private PessoaDTO pessoaDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.consultar(usuario), pessoaDTO));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.alterar(usuario), pessoaDTO));
    }
}
