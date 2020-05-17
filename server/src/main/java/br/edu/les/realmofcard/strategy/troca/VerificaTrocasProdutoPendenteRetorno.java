package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.EnviaEmailTrocaCodigoRastreio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Rastreio;
import br.edu.les.realmofcard.domain.Troca;
import br.edu.les.realmofcard.dao.RastreioDAO;
import br.edu.les.realmofcard.repository.TrocaRepository;

import java.util.Set;

@Component
public class VerificaTrocasProdutoPendenteRetorno implements IStrategy {

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private RastreioDAO rastreioDAO;

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
				rastreioDAO.consultar(rastreio);
				if(rastreio.getCodigoRastreio() != null){
					geraCodigoRastreio.processar(rastreio);
					rastreio = (Rastreio) rastreioDAO.salvar(rastreio);
					enviaEmailTrocaCodigoRastreio.processar(rastreio);
				}
			}
		}

		return null;
	}
}
