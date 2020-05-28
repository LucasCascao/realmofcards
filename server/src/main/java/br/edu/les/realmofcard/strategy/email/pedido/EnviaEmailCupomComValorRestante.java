package br.edu.les.realmofcard.strategy.email.pedido;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.repository.PessoaRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.EmailSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class EnviaEmailCupomComValorRestante implements IStrategy {
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private PessoaRepository pessoaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
    	
    	if(entidade instanceof Cupom) {

    		Cupom cupom = (Cupom) entidade;
    		
    		Usuario usuario = cupom.getPessoa().getUsuario();
    		
    		Pessoa cliente = pessoaRepository.findPessoaByUsuario_Id(usuario.getId());
    		
    		Mensagem mensagem = new Mensagem();
    		
    		StringBuilder mensagemTexto = new StringBuilder();
    		
    		mensagem.setAssunto("Foi gerado um cupom com o valor do cupom restante.");

    		mensagemTexto.append("Prezado " + cliente.getNome() + " " + cliente.getSobrenome() + ", ");
    		mensagemTexto.append("Devido restar um valor do cupom utilizado sua compra anterior foi gerado um novo cupom com o valor restante.\n");
    		mensagemTexto.append("Seu código de cupom é " + cupom.getCodigo() + " no valor de R$ " + cupom.getValor() + ".\n");
    		mensagemTexto.append("Caso queira realizar outra compra, peço que realize o pedido em nosso site.\n\n");
    		mensagemTexto.append("Realm of Cards agradece sua preferência e te desejamos um ótimo dia.");
    		
    		mensagem.setMensagem(mensagemTexto);
    		
    		emailSender.enviaEmail(usuario, mensagem);   		
    		
    	}
    	
        return null;
    }
}
