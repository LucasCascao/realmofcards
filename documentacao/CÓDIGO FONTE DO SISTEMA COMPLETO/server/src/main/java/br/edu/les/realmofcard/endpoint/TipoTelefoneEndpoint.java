package br.edu.les.realmofcard.endpoint;

import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.domain.TipoTelefone;
import br.edu.les.realmofcard.domain.TransacaoStatusCarta;
import br.edu.les.realmofcard.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tipos-telefone")
public class TipoTelefoneEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody TipoTelefone tipoTelefone){
        return ResponseEntity.ok().body(fachada.consultar(tipoTelefone));
    }
}
