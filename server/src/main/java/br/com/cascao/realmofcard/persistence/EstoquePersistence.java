package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoquePersistence implements IPersistence {

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
