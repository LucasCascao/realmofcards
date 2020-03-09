package br.com.cascao.realmofcard.web.fachada;

import br.com.cascao.realmofcard.dao.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbstractFachada {
    protected PessoaService pessoaService;
    @Autowired
    public AbstractFachada(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }
}
