package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/enderecos")
public class EnderecoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Endereco endereco){
        return ResponseEntity.ok().body(fachada.consultar(endereco));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Endereco endereco){
        return ResponseEntity.ok().body(fachada.salvar(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Endereco endereco = new Endereco();
        endereco.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(endereco));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Endereco endereco){
        return ResponseEntity.ok().body(fachada.alterar(endereco));
    }
}
