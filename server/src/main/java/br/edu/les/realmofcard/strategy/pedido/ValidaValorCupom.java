package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.dao.CupomDAO;
import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.repository.CartaRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.cupom.GeraCupomTroca;
import br.edu.les.realmofcard.strategy.email.EnviaEmailCupomComValorRestante;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidaValorCupom implements IStrategy {

    @Autowired
    private CupomDAO cupomDAO;

    @Autowired
    private GeraCupomTroca geraCupomTroca;

    @Autowired
    private EnviaEmailCupomComValorRestante enviaEmailCupomComValorRestante;

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;
            if(Util.isNotNull(pedido.getFormaPagamentoList())){
                for (FormaPagamento formaPagamento : pedido.getFormaPagamentoList()) {
                    if(Util.isNotNull(formaPagamento.getCupom())){
                        if(formaPagamento.getCupom().getValor() > pedido.getValorTotal()){
                            Cupom cupom = Cupom.builder()
                                    .valor(formaPagamento.getCupom().getValor() - pedido.getValorTotal())
                                    .pessoa(pedido.getCliente())
                                    .build();
                            geraCupomTroca.processar(cupom);
                            cupomDAO.salvar(cupom);
                            enviaEmailCupomComValorRestante.processar(cupom);
                        }
                    }
                }
            }
        }
        return null;
    }
}
