package br.com.cascao.realmofcard.negocio;

import br.com.cascao.realmofcard.domain.EntidadeDominio;

public interface IStrategy {
	
	public String processar(EntidadeDominio entidade);

}
