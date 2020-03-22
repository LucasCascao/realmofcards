package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.CartaoCredito;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cartaocredito")
public class CartaoCreditoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody CartaoCredito cartaoCredito){
        return ResponseEntity.ok().body(fachada.consultar(cartaoCredito));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody CartaoCredito cartaoCredito){
        return ResponseEntity.ok().body(fachada.salvar(cartaoCredito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(cartaoCredito));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody CartaoCredito cartaoCredito){
        return ResponseEntity.ok().body(fachada.alterar(cartaoCredito));
    }
}
