package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Bandeira;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.ItemTroca;
import br.com.cascao.realmofcard.domain.Troca;
import br.com.cascao.realmofcard.repository.BandeiraRepository;
import br.com.cascao.realmofcard.repository.ItemTrocaRepository;
import br.com.cascao.realmofcard.repository.TrocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrocaPersistence implements IPersistence {

    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private ItemTrocaRepository itemTrocaRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        Troca troca = (Troca) entidade;

        troca = trocaRepository.save(troca);

        for (ItemTroca itemTroca : troca.getItemListParaTroca()) {
            itemTroca.setTroca(troca);
            itemTrocaRepository.save(itemTroca);
        }


        return troca;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }
}
