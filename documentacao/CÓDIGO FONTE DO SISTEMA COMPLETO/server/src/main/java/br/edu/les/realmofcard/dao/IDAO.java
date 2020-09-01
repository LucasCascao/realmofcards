package br.edu.les.realmofcard.dao;

import java.util.List;

import br.edu.les.realmofcard.domain.EntidadeDominio;

public interface IDAO {
	
	public EntidadeDominio salvar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);

}
