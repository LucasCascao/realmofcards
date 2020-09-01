package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.Cidade;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.facade.Fachada;

@RestController
@CrossOrigin
@RequestMapping("/cidades")
public class CidadeEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Cidade cidade){
        return ResponseEntity.ok().body(fachada.consultar(cidade));
    }
}
