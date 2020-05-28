package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.dto.CarrinhoDTO;
import br.edu.les.realmofcard.dto.CartaDTO;
import br.edu.les.realmofcard.facade.Fachada;
import br.edu.les.realmofcard.util.DTOUtil;

@RestController
@CrossOrigin
@RequestMapping("/carrinhos")
public class CarrinhoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private CarrinhoDTO carrinhoDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Carrinho carrinho){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.consultar(carrinho),carrinhoDTO));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Carrinho carrinho){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.salvar(carrinho), carrinhoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Carrinho carrinho = new Carrinho();
        carrinho.setId(id);
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.excluir(carrinho), carrinhoDTO));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Carrinho carrinho){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.alterar(carrinho), carrinhoDTO));
    }
}
