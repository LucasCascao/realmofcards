package br.com.cascao.realmofcard.dao;

import java.util.List;

import br.com.cascao.realmofcard.domain.EntidadeDominio;

public interface IDAO {
	
	public void salvar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public EntidadeDominio visualizar(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);

}
