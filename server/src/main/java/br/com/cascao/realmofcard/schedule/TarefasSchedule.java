package br.com.cascao.realmofcard.schedule;

import br.com.cascao.realmofcard.domain.Cupom;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.domain.Troca;
import br.com.cascao.realmofcard.negocio.strategy.pedido.VerificaPedidosPagamentoPendente;
import br.com.cascao.realmofcard.negocio.strategy.troca.VerificaTrocasAprovadas;
import br.com.cascao.realmofcard.negocio.strategy.troca.VerificaTrocasProdutoPendenteRetorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@Component
@EnableScheduling
public class TarefasSchedule {

    @Autowired
    private VerificaTrocasAprovadas verificaTrocasAprovadas;

    @Autowired
    private VerificaPedidosPagamentoPendente verificaPedidosPagamentoPendente;
	
	private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60; 
    private final long HORA = MINUTO * 60;
    
    @Scheduled(initialDelay = 1000 * 3, fixedDelay = MINUTO)
    public void verificaPorHora() {
        verificaTrocasAprovadas.processar(new Troca());
        verificaPedidosPagamentoPendente.processar(new Pedido());
    }

}
