package br.edu.les.realmofcard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Estado;
import br.edu.les.realmofcard.repository.EstadoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoDAO implements IDAO {

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
