package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.CartaoCredito;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.dto.CartaoCreditoDTO;
import br.edu.les.realmofcard.facade.Fachada;
import br.edu.les.realmofcard.util.DTOUtil;

@RestController
@CrossOrigin
@RequestMapping("/cartaocredito")
public class CartaoCreditoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private CartaoCreditoDTO cartaoCreditoDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody CartaoCredito cartaoCredito){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.consultar(cartaoCredito), cartaoCreditoDTO));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody CartaoCredito cartaoCredito){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.salvar(cartaoCredito), cartaoCreditoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(cartaoCredito));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody CartaoCredito cartaoCredito){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.alterar(cartaoCredito), cartaoCreditoDTO));
    }
}
