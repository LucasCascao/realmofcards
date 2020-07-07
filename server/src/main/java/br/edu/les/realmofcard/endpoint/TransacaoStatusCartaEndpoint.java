package br.edu.les.realmofcard.endpoint;

import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.domain.TransacaoStatusCarta;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.dto.PessoaDTO;
import br.edu.les.realmofcard.facade.Fachada;
import br.edu.les.realmofcard.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/transacaos/status/cartas")
public class TransacaoStatusCartaEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody TransacaoStatusCarta transacaoStatusCarta){
        return ResponseEntity.ok().body(fachada.consultar(transacaoStatusCarta));
    }

    @PostMapping("/cria")
    public ResponseEntity<?> salvar(@RequestBody TransacaoStatusCarta transacaoStatusCarta){
        return ResponseEntity.ok().body(fachada.salvar(transacaoStatusCarta));
    }
}
