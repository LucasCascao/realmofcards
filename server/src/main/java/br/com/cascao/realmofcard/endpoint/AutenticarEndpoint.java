package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.web.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(methods = RequestMethod.POST)
@RequestMapping("/autenticar")
public class AutenticarEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    Resultado resultado;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Usuario usuario){
        resultado = fachada.consultar(usuario);
        Usuario usuarioAutenticado = (Usuario) resultado.getEntidades().get(0);
        if(usuarioAutenticado.getUsername() != null){
            usuarioAutenticado = null;
        }
        return ResponseEntity.ok().body(resultado);
    }
}
