package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Estado;
import br.com.cascao.realmofcard.repository.EnderecoRepository;
import br.com.cascao.realmofcard.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoPersistence implements IPersistence {

    @Autowired
    EstadoRepository estadoRepository;
    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) { return null; }

    @Override
    public void alterar(EntidadeDominio entidade) { }

    @Override
    public void excluir(EntidadeDominio entidade) { }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        if (entidade instanceof Estado){
            List<EntidadeDominio> estados = new ArrayList<>();
            estadoRepository.findAll()
                    .forEach( resultadoEndereco -> estados.add(resultadoEndereco));
            return estados;
        } return null;
    }
}
