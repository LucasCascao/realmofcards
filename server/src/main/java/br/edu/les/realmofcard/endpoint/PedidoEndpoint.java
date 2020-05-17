package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.facade.Fachada;

import java.text.DecimalFormat;

@RestController
@CrossOrigin
@RequestMapping("/pedidos")
public class PedidoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(fachada.consultar(pedido));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(fachada.salvar(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Pedido pedido = new Pedido();
        pedido.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(pedido));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(fachada.alterar(pedido));
    }
}
