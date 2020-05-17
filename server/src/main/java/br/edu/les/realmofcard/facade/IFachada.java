package br.edu.les.realmofcard.facade;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Resultado;

public interface IFachada {
	
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
}
