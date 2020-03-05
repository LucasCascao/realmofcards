package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.CardBean;
import br.com.cascao.realmofcard.domain.TypeBean;
import br.com.cascao.realmofcard.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carta")
public class CardEndpoint {

    @Autowired
    private CartaRepository cartaRepository;

    @GetMapping()
    public List<CardBean> get(){
        return cartaRepository.findAll();
    }

//    @GetMapping(value = "/{id}")
//    public CardBean get(@PathVariable Long id){
//        return cartaRepository.findById(id);
//    }

    @PostMapping()
    public CardBean post(@RequestParam CardBean cardBean){
        return cartaRepository.save(cardBean);
    }

}
