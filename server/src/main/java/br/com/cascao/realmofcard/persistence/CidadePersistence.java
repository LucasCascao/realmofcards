package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Cidade;
import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.repository.CidadeRepository;
import br.com.cascao.realmofcard.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CidadePersistence implements IPersistence {

    @Autowired
    CidadeRepository cidadeRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) { return null; }

    @Override
    public void alterar(EntidadeDominio entidade) { }

    @Override
    public void excluir(EntidadeDominio entidade) { }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        if (entidade instanceof Cidade){
            List<EntidadeDominio> cidades = new ArrayList<>();
            Cidade cidade = (Cidade) entidade;
            cidadeRepository.findByEstado_Id(cidade.getEstado().getId())
                    .forEach( resultadoCidade -> cidades.add(resultadoCidade));
            return cidades;
        } return null;
    }
}
