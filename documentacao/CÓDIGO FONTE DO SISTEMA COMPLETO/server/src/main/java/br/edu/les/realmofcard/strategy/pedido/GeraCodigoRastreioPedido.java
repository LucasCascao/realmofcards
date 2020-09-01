package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.dao.RastreioDAO;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.Rastreio;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.pedido.EnviaEmailPedidoCodigoRastreio;
import br.edu.les.realmofcard.util.GeradorCodigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoRastreioPedido implements IStrategy {

	@Autowired
	private RastreioDAO rastreioDAO;

	@Autowired
	private EnviaEmailPedidoCodigoRastreio enviaEmailPedidoCodigoRastreio;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Pedido){

			Pedido pedido = (Pedido) entidade;

			if(pedido.getStatusPedido() != null
				&& pedido.getStatusPedido().getId().equals(5)){

				Rastreio rastreio = new Rastreio();

				rastreio.setCodigoRastreio(GeradorCodigo.gerarCodigoRastreio());
				rastreio = (Rastreio) rastreioDAO.salvar(rastreio);
				pedido.setRastreio(rastreio);
				enviaEmailPedidoCodigoRastreio.processar(pedido);
			}
		}

		return null;
	}
}
