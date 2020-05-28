package br.edu.les.realmofcard.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.Cupom;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CupomRepository;

@Service
public class CupomDAO implements IDAO {

    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) { 
    	return cupomRepository.save((Cupom) entidade); 
    }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        List<EntidadeDominio> cupons = new ArrayList<>();

        Cupom cupom = (Cupom) entidade;

        if(Util.isNotNull(cupom.getCodigo())){
            cupons.add(cupomRepository.findCupomByCodigo(cupom.getCodigo()));
            return cupons;
        }

        cupomRepository.findAll().forEach( cupomResultado -> cupons.add(cupomResultado));

        return cupons;
    }
}
