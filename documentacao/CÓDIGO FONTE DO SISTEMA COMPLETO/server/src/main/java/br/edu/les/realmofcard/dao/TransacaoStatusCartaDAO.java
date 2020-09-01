package br.edu.les.realmofcard.dao;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.TransacaoStatusCarta;
import br.edu.les.realmofcard.repository.TransacaoStatusCartaRepository;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransacaoStatusCartaDAO implements IDAO {

    @Autowired
    private TransacaoStatusCartaRepository transacaoStatusCartaRepository;

    @Autowired
    private CartaDAO cartaDAO;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        TransacaoStatusCarta transacaoStatusCarta = (TransacaoStatusCarta) entidade;
        cartaDAO.alterar(transacaoStatusCarta.getCarta());
        return transacaoStatusCartaRepository.save(transacaoStatusCarta);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        TransacaoStatusCarta transacaoStatusCarta = (TransacaoStatusCarta) entidade;
        List<EntidadeDominio> transacoes = new ArrayList<>();

        if(Util.isNotNull(transacaoStatusCarta.getCarta())){
            transacoes.addAll(transacaoStatusCartaRepository.findByCarta_Id_OrderByCarta_Id(transacaoStatusCarta.getId()));
            return transacoes;
        }

        transacaoStatusCartaRepository.findAll().forEach(transacao -> transacoes.add(transacao));

        return transacoes;
    }
}
