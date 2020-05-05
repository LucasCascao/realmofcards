package br.com.cascao.realmofcard.negocio.strategy.troca;

import br.com.cascao.realmofcard.domain.Cupom;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Rastreio;
import br.com.cascao.realmofcard.domain.Troca;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.negocio.strategy.cupom.GeraCupomTroca;
import br.com.cascao.realmofcard.negocio.strategy.email.EnviaEmailTrocaAprovadaComCupom;
import br.com.cascao.realmofcard.negocio.strategy.email.EnviaEmailTrocaCodigoRastreio;
import br.com.cascao.realmofcard.persistence.CupomPersistence;
import br.com.cascao.realmofcard.persistence.PedidoPersistence;
import br.com.cascao.realmofcard.persistence.RastreioPersistence;
import br.com.cascao.realmofcard.repository.TrocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class VerificaTrocasProdutoPendenteRetorno implements IStrategy{

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private RastreioPersistence rastreioPersistence;

	@Autowired
	private GeraCodigoRastreio geraCodigoRastreio;

	@Autowired
	private EnviaEmailTrocaCodigoRastreio enviaEmailTrocaCodigoRastreio;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Troca){

			Set<Troca> trocasProdutoPendenteRecebimento = trocaRepository.getTrocasProdutoPendenteRecebimento();

			for (Troca trocaProdutoRecebimento : trocasProdutoPendenteRecebimento) {
				Rastreio rastreio = Rastreio.builder().troca(trocaProdutoRecebimento).build();
				rastreioPersistence.consultar(rastreio);
				if(rastreio.getCodigoRastreio() != null){
					geraCodigoRastreio.processar(rastreio);
					rastreio = (Rastreio) rastreioPersistence.salvar(rastreio);
					enviaEmailTrocaCodigoRastreio.processar(rastreio);
				}
			}
		}

		return null;
	}
}
