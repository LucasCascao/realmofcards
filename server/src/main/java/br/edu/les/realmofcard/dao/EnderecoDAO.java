package br.edu.les.realmofcard.dao;

import br.edu.les.realmofcard.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.Endereco;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CidadeRepository;
import br.edu.les.realmofcard.repository.EnderecoRepository;
import br.edu.les.realmofcard.util.Util;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoDAO implements IDAO {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco) {
            Endereco endereco = (Endereco) entidade;
            endereco.setCidade(cidadeRepository.findByNome(endereco.getCidade().getNome()));
            return enderecoRepository.save(endereco);
        }
        else return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco) entidade = enderecoRepository.save((Endereco) entidade);
        else entidade = null;
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;
        enderecoRepository.inativaEndereco(endereco.getId());
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco){
            List<EntidadeDominio> enderecos = new ArrayList<>();
            Endereco endereco = (Endereco) entidade;
            if(Util.isNotNull(endereco.getId())){
                enderecos.add(enderecoRepository.findById(endereco.getId()).get());
                return enderecos;
            }
            if(Util.isNotNull(endereco.getPessoa())
                    && Util.isNotNull(endereco.getPessoa().getId())
                    && Util.isNotNull(endereco.getStatus())){
                enderecos.addAll(enderecoRepository.findEnderecoByPessoaAndStatus(endereco.getPessoa().getId(), endereco.getStatus().getId()));
                return enderecos;
            }
            enderecoRepository.findByPessoa_Id(endereco.getPessoa().getId())
                    .forEach( resultadoEndereco -> enderecos.add(resultadoEndereco));
            return enderecos;
        } return null;
    }
}
