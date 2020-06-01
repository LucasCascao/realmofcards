package br.edu.les.realmofcard.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.ItemTransacao;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.repository.*;
import br.edu.les.realmofcard.util.Util;

@Service
public class TransacaoDAO implements IDAO {

    @Autowired
    private TransicaoRepository transicaoRepository;

    @Autowired
    private ItemTransicaoRepository itemTransicaoRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartaRepository cartaRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        Transicao transicao = (Transicao) entidade;

        transicao = transicaoRepository.save(transicao);

        for (ItemTransacao itemTransacao : transicao.getItemTransicaoList()) {
            itemTransacao.setTransicao(transicao);
            itemRepository.save(itemTransacao.getItem());
            itemTransicaoRepository.save(itemTransacao);
        }
        
        pedidoRepository.save(transicao.getPedido());

        return transicao;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
    	
    	Transicao transicao = (Transicao) entidade;
    	
    	for (ItemTransacao itemTransacao : transicao.getItemTransicaoList()) {
    		itemTransacao.setTransicao(transicao);
            itemTransicaoRepository.save(itemTransacao);
            itemRepository.save(itemTransacao.getItem());
            cartaRepository.save(itemTransacao.getItem().getCarta());
        }

    	transicaoRepository.save(transicao);
    	pedidoRepository.save(transicao.getPedido());
    }

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
    	
    	Transicao transicao = (Transicao) entidade;
    	
    	List<EntidadeDominio> trocas = new ArrayList<>();
    	
    	if(Util.isNotNull(transicao.getId())) {
    		trocas.add(transicaoRepository.findById(transicao.getId()).get());
    		return trocas;
    	}

        if(Util.isNotNull(transicao.getPedido())
                && Util.isNotNull(transicao.getPedido().getStatusPedido())
                && Util.isNotNull(transicao.getPedido().getStatusPedido().getId())
                && Util.isNotNull(transicao.getStatusTransacao())
                && Util.isNotNull(transicao.getStatusTransacao().getId())
                && Util.isNotNull(transicao.getTipoTransicao())
                && Util.isNotNull(transicao.getTipoTransicao().getId())) {

            Integer idStatusTransicao = transicao.getStatusTransacao().getId();
            Integer idStatusPedido = transicao.getPedido().getStatusPedido().getId();
            Integer idTipoTransicao = transicao.getTipoTransicao().getId();

            trocas.addAll(transicaoRepository.getTrocasByStatusAndPedidoStatus(idStatusTransicao, idStatusPedido, idTipoTransicao));
            return trocas;
        }
    	
    	if(Util.isNotNull(transicao.getPedido())
            && Util.isNotNull(transicao.getPedido().getStatusPedido())
    		&& Util.isNotNull(transicao.getPedido().getStatusPedido().getId())) {
    		trocas.addAll(transicaoRepository
    				.findByPedido_StatusPedido_Id(transicao.getPedido().getStatusPedido().getId()));
    		return trocas;
    	}
    	
    	transicaoRepository.findAll().forEach(trocaResultado -> trocas.add(trocaResultado));
    	
        return trocas;
    }
}
