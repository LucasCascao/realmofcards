package br.edu.les.realmofcard.endpoint;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.CartaoCredito;
import br.edu.les.realmofcard.domain.Cupom;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.facade.Fachada;
import br.edu.les.realmofcard.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cupons")
public class CupomEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Cupom cupom){
        return ResponseEntity.ok().body(fachada.consultar(cupom));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Cupom cupom){
        return ResponseEntity.ok().body(fachada.salvar(cupom));
    }
}
