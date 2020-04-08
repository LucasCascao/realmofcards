package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.Estado;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/estados")
public class EstadoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Estado estado){
        return ResponseEntity.ok().body(fachada.consultar(estado));
    }
}
