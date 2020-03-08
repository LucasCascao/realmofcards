package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.repository.PessoaRepository;
import br.com.cascao.realmofcard.web.fachada.Fachada;
import br.com.cascao.realmofcard.web.fachada.IFachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pessoas")
public class PessoaEndpoint {

    @Autowired
    private Pessoa pessoa;

    @Autowired
    private Fachada fachada;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping()
    public ResponseEntity<?>  get(){
        return ResponseEntity.ok(fachada.consultar(new Pessoa()).getEntidades());
    }

//    @GetMapping()
//    public ResponseEntity<?> get(){
//        List<Pessoa> pessoas = pessoaRepository.findAll();
//        return !pessoas.isEmpty() ? ResponseEntity.ok(pessoaRepository.findAll()) : ResponseEntity.noContent().build();
//    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody Pessoa pessoa){
        pessoa.setDtCadastro(LocalDateTime.now());
        return ResponseEntity.ok(pessoaRepository.save(pessoa));
    }

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> post(@RequestBody Pessoa pessoa){
//        pessoa.setDtCadastro(LocalDateTime.now());
//        return ResponseEntity.ok(fachada.salvar(pessoa));
//    }

//    @PostMapping()
//    public Pessoa post(@RequestBody Pessoa pessoa){
//        fachada.salvar(pessoa);
//        return pessoa;
//    }

//    @RequestMapping(value="/{id}", method=RequestMethod.GET)
//    public Pessoa get(@PathVariable int id){
//        this.pessoa.setId(id);
//        this.fachada.consultar(this.pessoa);
//        return this.pessoa;
//    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable Integer id){
        return ResponseEntity.ok(pessoaRepository.findById(id));
    }

    @DeleteMapping()
    public void delete(@RequestBody Pessoa pessoa){
        pessoaRepository.deleteById(pessoa.getId());
    }
}
