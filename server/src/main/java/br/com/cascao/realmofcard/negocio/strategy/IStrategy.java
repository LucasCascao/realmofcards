package br.com.cascao.realmofcard.negocio.strategy;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import org.springframework.stereotype.Component;

@Component
public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
