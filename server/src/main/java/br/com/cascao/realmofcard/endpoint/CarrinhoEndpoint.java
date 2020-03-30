package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.dto.CarrinhoDTO;
import br.com.cascao.realmofcard.dto.CartaDTO;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/carrinhos")
public class CarrinhoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    Resultado resultado;

    @Autowired
    CarrinhoDTO carrinhoDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Carrinho carrinho){
        resultado = fachada.consultar(carrinho);
        resultado.getEntidades().replaceAll( resultado -> carrinhoDTO.tranfereParaCarrinhoDTO((Carrinho) resultado));
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Carrinho carrinho){
        return ResponseEntity.ok().body(fachada.salvar(carrinho));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Carrinho carrinho = new Carrinho();
        carrinho.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(carrinho));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Carrinho carrinho){
        return ResponseEntity.ok().body(fachada.alterar(carrinho));
    }
}
