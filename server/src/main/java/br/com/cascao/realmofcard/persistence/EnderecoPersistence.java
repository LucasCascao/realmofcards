package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Endereco;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoPersistence implements IPersistence {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco) return enderecoRepository.save((Endereco) entidade);
        else return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco) entidade = enderecoRepository.save((Endereco) entidade);
        else entidade = null;
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        if (entidade instanceof Endereco){
            Endereco endereco = (Endereco) entidade;
            enderecoRepository.deleteById(endereco.getId());
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco){
            List<EntidadeDominio> enderecos = new ArrayList<>();
            Endereco endereco = (Endereco) entidade;
//            if(endereco.getId() != null){
//                enderecoRepository.findByUsuario_Id(endereco.getId()).stream().
//                        map( resultadoEndereco -> enderecos.add(resultadoEndereco));
//            }
            return enderecos;
        } return null;
    }
}
