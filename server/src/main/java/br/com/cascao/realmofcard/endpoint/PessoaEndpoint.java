package br.com.cascao.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Resultado> consultar(Pessoa pessoa){
        return ResponseEntity.ok().body(fachada.consultar(pessoa));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok(fachada.salvar(pessoa));
    }

  /*@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable Integer id){
        return ResponseEntity.ok(pessoaRepository.findById(id));
    }

    @DeleteMapping()
    public void delete(@RequestBody Pessoa pessoa){
        pessoaRepository.deleteById(pessoa.getId());
    }*/
}
