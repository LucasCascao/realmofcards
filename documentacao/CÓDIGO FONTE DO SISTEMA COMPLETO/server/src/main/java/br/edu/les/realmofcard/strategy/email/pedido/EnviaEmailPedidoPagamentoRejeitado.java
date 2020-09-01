package br.edu.les.realmofcard.strategy.email.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Mensagem;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.repository.PessoaRepository;
import br.edu.les.realmofcard.util.EmailSender;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EnviaEmailPedidoPagamentoRejeitado implements IStrategy {
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private PessoaRepository pessoaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
    	
    	if(entidade instanceof Pedido) {
    		
    		Pedido pedido = (Pedido) entidade;
    		
    		Usuario usuario = pedido.getCliente().getUsuario();
    		
    		Pessoa cliente = pessoaRepository.findPessoaByUsuario_Id(usuario.getId());
    		
    		Mensagem mensagem = new Mensagem();
    		
    		StringBuilder mensagemTexto = new StringBuilder();
    		
    		mensagem.setAssunto("Pagamento do pedido " + pedido.getCodigoPedido() + " foi rejeitado.");
    		
    		mensagemTexto.append("Prezado " + cliente.getNome() + " " + cliente.getSobrenome() + ", ");
    		mensagemTexto.append("este email foi enviado para informar que o pagamento não foi aprovado para o pedido " + pedido.getCodigoPedido() + ".\n");
    		mensagemTexto.append("Caso queira realizar a compra, peço que realize novamente o pedido em nosso site.\n\n");
    		mensagemTexto.append("Realm of Cards agradece sua coompreenção e te desejamos um ótimo dia.");
    		
    		mensagem.setMensagem(mensagemTexto);
    		
    		emailSender.enviaEmail(usuario, mensagem);   		
    		
    	}
    	
        return null;
    }
}
