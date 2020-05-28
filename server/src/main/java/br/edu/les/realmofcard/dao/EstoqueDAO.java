package br.edu.les.realmofcard.dao;

import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.EntidadeDominio;

import java.util.List;

@Service
public class EstoqueDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }
}
