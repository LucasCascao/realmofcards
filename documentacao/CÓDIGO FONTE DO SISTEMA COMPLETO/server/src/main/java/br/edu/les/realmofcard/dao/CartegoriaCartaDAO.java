package br.edu.les.realmofcard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.CategoriaCarta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CategoriaCartaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartegoriaCartaDAO implements IDAO {

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
