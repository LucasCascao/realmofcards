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
public class EnviaEmailPedidoPendentePagamento implements IStrategy {
	
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
    		
    		mensagem.setAssunto("Pedido pendente pagamento para o pedido " + pedido.getCodigoPedido() + ".");

    		mensagemTexto.append("Prezado " + cliente.getNome() + " " + cliente.getSobrenome() + ", ");
    		mensagemTexto.append("este email foi enviado para comunicar que seu pedido está sendo processado e está aguardando o recebimento do pagamento.\n");
    		mensagemTexto.append("Caso queira realizar outra compra, peço que realize o pedido em nosso site.\n\n");
    		mensagemTexto.append("Realm of Cards agradece sua preferência e te desejamos um ótimo dia.");
    		
    		mensagem.setMensagem(mensagemTexto);
    		
    		emailSender.enviaEmail(usuario, mensagem);   		
    		
    	}
    	
        return null;
    }
}
