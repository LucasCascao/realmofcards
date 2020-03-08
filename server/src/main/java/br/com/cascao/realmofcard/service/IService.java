package br.com.cascao.realmofcard.service;

import java.util.List;

import br.com.cascao.realmofcard.domain.EntidadeDominio;

public interface IService {
	
	public EntidadeDominio salvar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public EntidadeDominio visualizar(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);

}
