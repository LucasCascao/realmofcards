package br.edu.les.realmofcard.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.strategy.pedido.VerificaPedidosPagamentoPendente;
import br.edu.les.realmofcard.strategy.transicao.VerificaTransacoesAprovadas;

@Component
@EnableScheduling
public class TarefasSchedule {

    @Autowired
    private VerificaTransacoesAprovadas verificaTransacoesAprovadas;

    @Autowired
    private VerificaPedidosPagamentoPendente verificaPedidosPagamentoPendente;
	
	private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60; 
    private final long HORA = MINUTO * 60;
    
    @Scheduled(initialDelay = 1000 * 3, fixedDelay = MINUTO)
    public void verificaPorHora() {
        verificaTransacoesAprovadas.processar(new Transicao());
        verificaPedidosPagamentoPendente.processar(new Pedido());
    }

}
