package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.web.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(fachada.consultar(usuario));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(fachada.alterar(usuario));
    }
}
