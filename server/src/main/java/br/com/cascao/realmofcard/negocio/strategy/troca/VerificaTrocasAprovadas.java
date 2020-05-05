package br.com.cascao.realmofcard.negocio.strategy.troca;

import java.util.List;
import java.util.Set;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.cupom.GeraCupomTroca;
import br.com.cascao.realmofcard.negocio.strategy.email.EnviaEmailTrocaAprovadaComCupom;
import br.com.cascao.realmofcard.persistence.CupomPersistence;
import br.com.cascao.realmofcard.persistence.PedidoPersistence;
import br.com.cascao.realmofcard.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.TrocaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class VerificaTrocasAprovadas implements IStrategy{

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private CupomRepository cupomRepository;

	@Autowired
	private CupomPersistence cupomPersistence;

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
					cupom = (Cupom) cupomPersistence.salvar(cupom);
					enviaEmailTrocaAprovadaComCupom.processar(cupom);
				}
			}
		}

		return msg.toString();
	}
}
