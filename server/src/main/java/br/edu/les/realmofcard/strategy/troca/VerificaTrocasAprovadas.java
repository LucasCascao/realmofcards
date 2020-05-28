package br.edu.les.realmofcard.strategy.troca;

import java.util.Set;

import br.edu.les.realmofcard.dao.PedidoDAO;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.cupom.GeraCupomTroca;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaAprovadaComCupom;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.dao.CupomDAO;
import br.edu.les.realmofcard.repository.TrocaRepository;

@Component
public class VerificaTrocasAprovadas implements IStrategy {

	@Autowired
	private TrocaRepository trocaRepository;

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

		if(entidade instanceof Troca){

			Set<Troca> trocasAprovadas = trocaRepository.getTrocasAprovadas();

			for (Troca trocaAprovada : trocasAprovadas) {
				if(Util.isNull(trocaAprovada.getCupom())){
					Cupom cupom = Cupom.builder()
							.valor(trocaAprovada.getSubTotal())
							.pessoa(trocaAprovada.getPedidoParaTroca().getCliente())
							.build();
					geraCupomTroca.processar(cupom);
					trocaAprovada.setCupom((Cupom) cupomDAO.salvar(cupom));
					enviaEmailTrocaAprovadaComCupom.processar(trocaAprovada);
					trocaAprovada.getPedidoParaTroca().setStatusPedido(StatusPedido.builder().id(6).build());
					pedidoDAO.alterar(trocaAprovada.getPedidoParaTroca());
				}
			}
		}

		return msg.toString();
	}
}
