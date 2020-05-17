package br.edu.les.realmofcard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.CartaoCredito;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CartaoCreditoRepository;
import br.edu.les.realmofcard.util.Util;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartaoCreditoDAO implements IDAO {

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof CartaoCredito) return cartaoCreditoRepository.save((CartaoCredito) entidade);
        else return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if(entidade instanceof CartaoCredito) entidade = cartaoCreditoRepository.save((CartaoCredito) entidade);
        else entidade = null;
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        if(entidade instanceof CartaoCredito){
            CartaoCredito cartaoCredito = (CartaoCredito) entidade;
            cartaoCreditoRepository.deleteById(cartaoCredito.getId());
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        if(entidade instanceof CartaoCredito){
            List<EntidadeDominio> cartoesCredito = new ArrayList<>();
            CartaoCredito cartaoCredito = (CartaoCredito) entidade;

            if(Util.isNotNull(cartaoCredito.getId())){
                cartoesCredito.add(cartaoCreditoRepository.findById(cartaoCredito.getId()).get());
                return cartoesCredito;
            }

            if(Util.isNotNull(cartaoCredito.getPreferido())){
                cartaoCreditoRepository
                        .findByPessoa_IdAndPreferido(cartaoCredito.getPessoa().getId(), cartaoCredito.getPreferido())
                        .forEach(cartaoCreditoResultado -> cartoesCredito.add(cartaoCreditoResultado));
                return cartoesCredito;
            }

            cartaoCreditoRepository.findByPessoa_Id(cartaoCredito.getPessoa().getId())
                    .forEach(resultado -> cartoesCredito.add(resultado));
            return cartoesCredito;
        } else return null;
    }
}
