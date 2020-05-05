package br.com.cascao.realmofcard.negocio.strategy.email;

import br.com.cascao.realmofcard.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.PessoaRepository;
import br.com.cascao.realmofcard.util.EmailSender;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EnviaEmailTrocaAprovadaComCupom implements IStrategy {
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private PessoaRepository pessoaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
    	
    	if(entidade instanceof Cupom) {
    		
    		Cupom cupom = (Cupom) entidade;

    		Pedido pedido = cupom.getTroca().getPedidoParaTroca();
    		
    		Usuario usuario = pedido.getCliente().getUsuario();
    		
    		Pessoa cliente = pessoaRepository.findPessoaByUsuario_Id(usuario.getId());
    		
    		Mensagem mensagem = new Mensagem();
    		
    		StringBuilder mensagemTexto = new StringBuilder();
    		
    		mensagem.setAssunto("Solicitação de troco do pedido " + pedido.getCodigoPedido() + " foi aprovado.");

    		mensagemTexto.append("Prezado " + cliente.getNome() + " " + cliente.getSobrenome() + ", ");
    		mensagemTexto.append("este email foi enviado para informar que a solicitação de troca foi aprovado para o pedido " + pedido.getCodigoPedido() + ".\n");
    		mensagemTexto.append("Seu código de cupom é " + cupom.getCodigo() + " no valor de R$ " + cupom.getValor() + ".\n");
    		mensagemTexto.append("Caso queira realizar outra compra, peço que realize o pedido em nosso site.\n\n");
    		mensagemTexto.append("Realm of Cards agradece sua preferência e te desejamos um ótimo dia.");
    		
    		mensagem.setMensagem(mensagemTexto);
    		
    		emailSender.enviaEmail(usuario, mensagem);   		
    		
    	}
    	
        return null;
    }
}
