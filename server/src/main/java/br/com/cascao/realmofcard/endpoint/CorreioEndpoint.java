package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Bandeira;
import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import br.com.cascao.realmofcard.util.correio.WebServiceCorreio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    	resultado.addEntidade(new WebServiceCorreio().calculaPrecoPrazo(endereco.getCep()));
        return ResponseEntity.ok().body(resultado);
    }
}
