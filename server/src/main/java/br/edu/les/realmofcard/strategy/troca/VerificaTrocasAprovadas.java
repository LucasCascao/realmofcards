package br.edu.les.realmofcard.strategy.troca;

import java.util.Set;

import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.cupom.GeraCupomTroca;
import br.edu.les.realmofcard.strategy.email.EnviaEmailTrocaAprovadaComCupom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.dao.CupomDAO;
import br.edu.les.realmofcard.repository.CupomRepository;
import br.edu.les.realmofcard.repository.TrocaRepository;

@Component
public class VerificaTrocasAprovadas implements IStrategy {

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private CupomRepository cupomRepository;

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
				if(cupomRepository.findByTroca_Id(trocaAprovada.getId()).size() == 0){
					Cupom cupom = Cupom.builder().troca(trocaAprovada).build();
					geraCupomTroca.processar(cupom);
					cupom = (Cupom) cupomDAO.salvar(cupom);
					enviaEmailTrocaAprovadaComCupom.processar(cupom);
				}
			}
		}

		return msg.toString();
	}
}
