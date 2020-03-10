package br.com.cascao.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.web.fachada.Fachada;

@RestController
@CrossOrigin
@RequestMapping("/pessoas")
public class PessoaEndpoint {

    /*@Autowired
    private PessoaService pessoaService;*/
    
    @Autowired
    private Fachada fachada;

    
    @GetMapping
    public ResponseEntity<Resultado> consultar(){
        return ResponseEntity.ok().body(fachada.consultar(new Pessoa()));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok(fachada.salvar(pessoa));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok().body(fachada.excluir(pessoa));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok().body(fachada.alterar(pessoa));
    }
}
