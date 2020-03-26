package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.CategoriaCarta;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/categorias")
public class CategoriaCartaEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody CategoriaCarta categoriaCarta){
        return ResponseEntity.ok().body(fachada.consultar(categoriaCarta));
    }
}
