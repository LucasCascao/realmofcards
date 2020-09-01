package br.edu.les.realmofcard.dao;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.GrupoPrecificacao;
import br.edu.les.realmofcard.domain.TransacaoStatusCarta;
import br.edu.les.realmofcard.repository.GrupoPrecificacaoRepository;
import br.edu.les.realmofcard.repository.TransacaoStatusCartaRepository;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GrupoPrecificacaoDAO implements IDAO {

    @Autowired
    private GrupoPrecificacaoRepository grupoPrecificacaoRepository;

    @Autowired
    private CartaDAO cartaDAO;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        GrupoPrecificacao grupoPrecificacao = (GrupoPrecificacao) entidade;
        return grupoPrecificacaoRepository.save(grupoPrecificacao);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        GrupoPrecificacao grupoPrecificacao = (GrupoPrecificacao) entidade;
        List<EntidadeDominio> gruposPrecificacao = new ArrayList<>();

        grupoPrecificacaoRepository.findAll().forEach(transacao -> gruposPrecificacao.add(transacao));

        return gruposPrecificacao;
    }
}
