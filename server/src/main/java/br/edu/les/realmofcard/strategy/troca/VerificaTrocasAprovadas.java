package br.edu.les.realmofcard.strategy.troca;

import java.util.Set;

import br.edu.les.realmofcard.dao.PedidoDAO;
import br.edu.les.realmofcard.dao.TransacaoDAO;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.cupom.GeraCupomTroca;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaAprovadaComCupom;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.dao.CupomDAO;
import br.edu.les.realmofcard.repository.TransicaoRepository;

@Component
public class VerificaTrocasAprovadas implements IStrategy {

	@Autowired
	private TransicaoRepository transicaoRepository;

	@Autowired
	private TransacaoDAO transacaoDAO;

	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private CupomDAO cupomDAO;

	@Autowired
	private GeraCupomTroca geraCupomTroca;

	@Autowired
	private EnviaEmailTrocaAprovadaComCupom enviaEmailTrocaAprovadaComCupom;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Transicao){

			Set<Transicao> trocasAprovadas = transicaoRepository.getTrocasAprovadas();

			for (Transicao transicaoAprovada : trocasAprovadas) {
				if(Util.isNull(transicaoAprovada.getCupom())){
					Cupom cupom = Cupom.builder()
							.valor(transicaoAprovada.getSubTotal())
							.pessoa(transicaoAprovada.getPedido().getCliente())
							.build();
					if(transicaoAprovada.getTipoTransicao().getId() == 1){
						cupom.setTipoCupom(TipoCupom.builder().id(1).build());
					} else if(transicaoAprovada.getTipoTransicao().getId() == 2){
						cupom.setTipoCupom(TipoCupom.builder().id(2).build());
					}
					geraCupomTroca.processar(cupom);
					transicaoAprovada.setCupom((Cupom) cupomDAO.salvar(cupom));
					enviaEmailTrocaAprovadaComCupom.processar(transicaoAprovada);
					transicaoAprovada.getPedido().setStatusPedido(StatusPedido.builder().id(6).build());
					pedidoDAO.alterar(transicaoAprovada.getPedido());
					transicaoAprovada.setStatusTransacao(StatusTransacao.builder().id(2).build());
					transacaoDAO.alterar(transicaoAprovada);
				}
			}
		}

		return msg.toString();
	}
}
