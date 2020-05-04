package br.com.cascao.realmofcard.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.ItemTroca;
import br.com.cascao.realmofcard.domain.Troca;
import br.com.cascao.realmofcard.repository.ItemRepository;
import br.com.cascao.realmofcard.repository.ItemTrocaRepository;
import br.com.cascao.realmofcard.repository.PedidoRepository;
import br.com.cascao.realmofcard.repository.TrocaRepository;
import br.com.cascao.realmofcard.util.Util;

@Service
public class TrocaPersistence implements IPersistence {

    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private ItemTrocaRepository itemTrocaRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        Troca troca = (Troca) entidade;

        troca = trocaRepository.save(troca);

        for (ItemTroca itemTroca : troca.getItemListParaTroca()) {
            itemTroca.setTroca(troca);
            itemTrocaRepository.save(itemTroca);
        }
        
        pedidoRepository.save(troca.getPedidoParaTroca());

        return troca;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
    	
    	Troca troca = (Troca) entidade;
    	
    	for (ItemTroca itemTroca : troca.getItemListParaTroca()) {
    		itemTroca.setTroca(troca);
            itemTrocaRepository.save(itemTroca);
            itemRepository.save(itemTroca.getItemParaTroca());
        }
    	
    	pedidoRepository.save(troca.getPedidoParaTroca());
    }

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
    	
    	Troca troca = (Troca) entidade;
    	
    	List<EntidadeDominio> trocas = new ArrayList<>();
    	
    	if(Util.isNotNull(troca.getId())) {
    		trocas.add(trocaRepository.findById(troca.getId()).get());
    		return trocas;
    	}
    	
    	if(Util.isNotNull(troca.getPedidoParaTroca())
    		&& Util.isNotNull(troca.getPedidoParaTroca().getId())) {
    		trocas.addAll(trocaRepository
    				.findByPedidoParaTroca_StatusPedido_Id(troca.getPedidoParaTroca().getId()));
    		return trocas;
    	}
    	
    	trocaRepository.findAll().forEach(trocaResultado -> trocas.add(trocaResultado));
    	
        return trocas;
    }
}
