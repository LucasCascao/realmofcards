package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Bandeira;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Rastreio;
import br.com.cascao.realmofcard.repository.BandeiraRepository;
import br.com.cascao.realmofcard.repository.RastreioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RastreioPersistence implements IPersistence {

    @Autowired
    private RastreioRepository rastreioRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return rastreioRepository.save((Rastreio) entidade);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> rastrios = new ArrayList<>();
        if(entidade instanceof Rastreio){
            Rastreio rastreio = (Rastreio) entidade;

            if(rastreio.getTroca() != null){
                rastreio.setCodigoRastreio(rastreioRepository.findByTroca_Id(rastreio.getTroca().getId()).getCodigoRastreio());
            }

            rastreioRepository.findAll().forEach( bandeira -> rastrios.add(bandeira));
            return rastrios;
        } else return null;
    }
}
