package br.edu.les.realmofcard.dao;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Telefone;
import br.edu.les.realmofcard.repository.TelefoneRepository;
import br.edu.les.realmofcard.repository.TipoTelefoneRepository;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelefoneDAO implements IDAO {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return telefoneRepository.save((Telefone) entidade);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
    	
    	List<EntidadeDominio> telefones = new ArrayList<>();
        Telefone telefone = (Telefone) entidade;

        if(Util.isNotNull(telefone.getPessoa()) && Util.isNotNull(telefone.getPessoa().getId())){
            telefones.addAll(telefoneRepository.findByPessoa_Id(telefone.getPessoa().getId()));
            return telefones;
        }
    	
    	telefoneRepository.findAll().forEach(tipoTelefone -> telefones.add(tipoTelefone));
    	
        return telefones;
    }
}
