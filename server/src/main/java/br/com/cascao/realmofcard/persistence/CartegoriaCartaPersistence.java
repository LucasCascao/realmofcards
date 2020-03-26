package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.CartaoCredito;
import br.com.cascao.realmofcard.domain.CategoriaCarta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.repository.CartaoCreditoRepository;
import br.com.cascao.realmofcard.repository.CategoriaCartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartegoriaCartaPersistence implements IPersistence {

    @Autowired
    CategoriaCartaRepository categoriaCartaRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) { return null; }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> categorias = new ArrayList<>();
        if(entidade instanceof CategoriaCarta){
            categoriaCartaRepository.findAll()
                    .forEach(resultado -> categorias.add(resultado));
            return categorias;
        } else return null;
    }
}
