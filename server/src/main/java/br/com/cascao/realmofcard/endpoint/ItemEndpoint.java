package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/itens")
public class ItemEndpoint {
    
    @Autowired
    private Fachada fachada;

    @DeleteMapping("/{id}")
    public ResponseEntity<Resultado> delete(@PathVariable Integer id){
        Item item = new Item();
        item.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(item));
    }
}
