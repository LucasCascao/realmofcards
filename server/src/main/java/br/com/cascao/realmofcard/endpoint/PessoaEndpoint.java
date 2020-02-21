package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.web.fachada.Fachada;
import br.com.cascao.realmofcard.web.fachada.IFachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pessoas")

public class PessoaEndpoint {

    private Pessoa pessoa;

    @GetMapping()
    public List<EntidadeDominio> get(){
        return new Fachada().consultar(new Pessoa()).getEntidades();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Pessoa get(@PathVariable int id){
        this.pessoa = new Pessoa();
        this.pessoa.setId(id);
        new Fachada().consultar(this.pessoa);
        return this.pessoa;
    }

}
