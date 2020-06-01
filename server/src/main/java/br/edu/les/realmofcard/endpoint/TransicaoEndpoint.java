package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.facade.Fachada;

@RestController
@CrossOrigin
@RequestMapping("/transicoes")
public class TransicaoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Transicao transicao){
        return ResponseEntity.ok().body(fachada.consultar(transicao));
    }

    @PostMapping(path = "/cria")
    public ResponseEntity<Resultado> salvar(@RequestBody Transicao transicao){
        return ResponseEntity.ok().body(fachada.salvar(transicao));
    }
    
    @PutMapping()
    public ResponseEntity<Resultado> alterar(@RequestBody Transicao transicao){
    	return ResponseEntity.ok().body(fachada.alterar(transicao));
    }
}
