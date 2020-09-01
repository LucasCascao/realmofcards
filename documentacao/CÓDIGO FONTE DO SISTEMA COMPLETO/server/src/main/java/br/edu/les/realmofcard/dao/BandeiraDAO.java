package br.edu.les.realmofcard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.BandeiraRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BandeiraDAO implements IDAO {

    @Autowired
    private BandeiraRepository bandeiraRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) { return null; }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> bandeiras = new ArrayList<>();
        if(entidade instanceof Bandeira){
            bandeiraRepository.findAll().forEach( bandeira -> bandeiras.add(bandeira));
            return bandeiras;
        } else return null;
    }
}
