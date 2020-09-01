package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.CategoriaCarta;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.facade.Fachada;

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
