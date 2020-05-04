package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Bandeira;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.domain.Troca;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/trocas")
public class TrocaEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Troca troca){
        return ResponseEntity.ok().body(fachada.consultar(troca));
    }

    @PostMapping(path = "/cria")
    public ResponseEntity<Resultado> salvar(@RequestBody Troca troca){
        return ResponseEntity.ok().body(fachada.salvar(troca));
    }
    
    @PutMapping()
    public ResponseEntity<Resultado> alterar(@RequestBody Troca troca){
    	return ResponseEntity.ok().body(fachada.alterar(troca));
    }
}
