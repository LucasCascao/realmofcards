package br.edu.les.realmofcard.strategy;

import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;

@Component
public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
