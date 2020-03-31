package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.dto.CartaDTO;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cartas")
public class CartaEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Carta carta){
        return ResponseEntity.ok().body(fachada.consultar(carta));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody CartaDTO cartaDTO){
        Carta carta = (Carta) cartaDTO.getEntidade(cartaDTO);
        return ResponseEntity.ok().body(fachada.salvar(carta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Carta carta = new Carta();
        carta.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(carta));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Carta carta){
        return ResponseEntity.ok().body(fachada.alterar(carta));
    }
}
