package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Status;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartaPersistence implements IPersistence {

    @Autowired
    CartaRepository cartaRepository;

    @Autowired
    StatusRepository statusRepository;

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
        if(entidade instanceof Carta) {
            Carta carta = (Carta) entidade;
            carta = cartaRepository.findById(carta.getId()).get();
            carta.setStatus( statusRepository.findById(carta.getStatus().getId() == 1 ? 2 : 1).get());
            cartaRepository.save(carta);
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> cartas = new ArrayList<>();
        if (entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            if(carta.getStatus() != null){
                if (carta.getStatus().getId()  >= 1 && carta.getStatus().getId() <= 2){
                    cartaRepository.findByStatus_Id(carta.getStatus().getId())
                            .forEach( resultado -> cartas.add(resultado));
                }
            }
            if (carta.getId() != null){
                cartas.add(cartaRepository.findById(carta.getId()).get());
                return cartas;
            }
            return cartas;
        }
        return null;
    }
}
