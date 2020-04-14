package br.com.cascao.realmofcard.negocio.fachada;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Resultado;

public interface IFachada {
	
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
}
