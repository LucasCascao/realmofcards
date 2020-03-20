package br.com.cascao.realmofcard.persistence;

import java.util.List;

import br.com.cascao.realmofcard.domain.EntidadeDominio;

public interface IPersistence {
	
	public EntidadeDominio salvar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);

}
