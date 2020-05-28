package br.edu.les.realmofcard.dao;

import br.edu.les.realmofcard.domain.Status;
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
        CartaoCredito cartaoCredito = (CartaoCredito) entidade;
        cartaoCreditoRepository.inativaCartaoCredito(cartaoCredito.getId());
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
            if(Util.isNotNull(cartaoCredito.getPessoa()) && Util.isNotNull(cartaoCredito.getPessoa().getId())
                && Util.isNotNull(cartaoCredito.getStatus()) && Util.isNotNull(cartaoCredito.getStatus().getId())){

                cartoesCredito.addAll(cartaoCreditoRepository.findEnderecoByPessoaAndStatus(cartaoCredito.getPessoa().getId(), cartaoCredito.getStatus().getId()));
                return cartoesCredito;
            }
            cartaoCreditoRepository.findByPessoa_Id(cartaoCredito.getPessoa().getId())
                    .forEach(resultado -> cartoesCredito.add(resultado));
            return cartoesCredito;
        } else return null;
    }
}
