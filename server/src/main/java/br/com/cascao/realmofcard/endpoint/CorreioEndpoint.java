package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Bandeira;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/correios")
public class CorreioEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Bandeira bandeira){
        return ResponseEntity.ok().body(fachada.consultar(bandeira));
    }
}
