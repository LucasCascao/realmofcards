package br.edu.les.realmofcard.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.dto.CartaDTO;
import br.edu.les.realmofcard.facade.Fachada;
import br.edu.les.realmofcard.util.DTOUtil;
import br.edu.les.realmofcard.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cartas")
public class CartaEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private CartaDTO cartaDTO;

    @Autowired
    private Resultado resultado;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Carta carta){
        resultado = fachada.consultar(carta);
        if(!resultado.getEntidades().isEmpty()){
            List<Carta> cartas = new ArrayList<>();
            resultado.getEntidades().forEach(entidadeDominio -> cartas.add((Carta) entidadeDominio));
            Collections.sort(cartas);
            resultado.setEntidades(new ArrayList<>());
            cartas.forEach( cartaOrdenada -> resultado.addEntidade(cartaOrdenada));
        }
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Carta carta){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.salvar(carta), cartaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Carta carta = new Carta();
        carta.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(carta));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Carta carta){
        return ResponseEntity.ok().body(fachada.alterar(carta));
    }
}
