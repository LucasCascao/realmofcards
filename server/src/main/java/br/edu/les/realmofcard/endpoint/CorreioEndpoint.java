package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.Endereco;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.facade.Fachada;
import br.edu.les.realmofcard.util.correio.WebServiceCorreio;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/correios")
public class CorreioEndpoint {
    
    @Autowired
    private Fachada fachada;
    
    @Autowired
    private Resultado resultado;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Endereco endereco){
    	resultado.setEntidades(new ArrayList<>());
    	resultado.addEntidade(new WebServiceCorreio().calculaPrecoPrazo(endereco.getCep()));
        return ResponseEntity.ok().body(resultado);
    }
}
