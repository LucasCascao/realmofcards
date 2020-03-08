package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.repository.PessoaRepository;
import br.com.cascao.realmofcard.web.fachada.Fachada;
import br.com.cascao.realmofcard.web.fachada.IFachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/pessoas")
public class PessoaEndpoint {

    @Autowired
    private Pessoa pessoa;

    @Autowired
    private Fachada fachada;

    @Autowired
    private PessoaRepository pessoaRepository;

//    @GetMapping()
//    public List<EntidadeDominio> get(){
//        return fachada.consultar(new Pessoa()).getEntidades();
//    }

    @GetMapping()
    public List<Pessoa> get(){
        return pessoaRepository.findAll();
    }

    @PostMapping()
    public Pessoa post(@RequestBody Pessoa pessoa){
        pessoa.setDtCadastro(LocalDateTime.now());
        return pessoaRepository.save(pessoa);
    }

//    @PostMapping()
//    public Pessoa post(@RequestBody Pessoa pessoa){
//        fachada.salvar(pessoa);
//        return pessoa;
//    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Pessoa get(@PathVariable int id){
        this.pessoa.setId(id);
        this.fachada.consultar(this.pessoa);
        return this.pessoa;
    }

    @DeleteMapping()
    public void delete(@RequestBody Pessoa pessoa){
        pessoaRepository.deleteById(pessoa.getId());
    }
}
