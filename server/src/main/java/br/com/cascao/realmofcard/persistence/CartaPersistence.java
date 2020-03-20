package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartaPersistence implements IPersistence {

    @Autowired
    CartaRepository cartaRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof Carta) return cartaRepository.save((Carta) entidade);
        else return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if(entidade instanceof Carta) entidade = cartaRepository.save((Carta) entidade);
        else entidade = null;
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        if(entidade instanceof Carta) cartaRepository.deleteById(((Carta) entidade).getId());
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> cartas = new ArrayList<>();
        if (entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            if (carta.getId() != null){
                cartaRepository.findById(carta.getId()).map( cartaEncontrada -> cartas.add(cartaEncontrada));
                return cartas;
            }
            cartaRepository.findAll().forEach(cartaEncontrada -> cartas.add(cartaEncontrada));
            return cartas;
        }
        return null;
    }
}
