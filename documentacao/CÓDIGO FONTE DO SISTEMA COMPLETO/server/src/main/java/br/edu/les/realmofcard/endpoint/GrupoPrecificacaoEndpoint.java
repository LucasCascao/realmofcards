package br.edu.les.realmofcard.endpoint;

import br.edu.les.realmofcard.domain.GrupoPrecificacao;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.domain.TransacaoStatusCarta;
import br.edu.les.realmofcard.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/grupo-precificacao")
public class GrupoPrecificacaoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody GrupoPrecificacao grupoPrecificacao){
        return ResponseEntity.ok().body(fachada.consultar(grupoPrecificacao));
    }

    @PostMapping("/cria")
    public ResponseEntity<?> salvar(@RequestBody GrupoPrecificacao grupoPrecificacao){
        return ResponseEntity.ok().body(fachada.salvar(grupoPrecificacao));
    }
}
