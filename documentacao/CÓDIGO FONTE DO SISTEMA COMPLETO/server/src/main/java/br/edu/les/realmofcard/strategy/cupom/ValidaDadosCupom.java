package br.edu.les.realmofcard.strategy.cupom;

import br.edu.les.realmofcard.domain.Cupom;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.UsuarioRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosCupom implements IStrategy {

	@Autowired
	private ValidadorString validadorString;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public String processar(final EntidadeDominio entidade) {
		StringBuilder msg = new StringBuilder();
		if(entidade instanceof Cupom){
			Cupom cupom = (Cupom) entidade;
			if(Util.isNull(cupom.getValor())){
				msg.append(validadorString.validar(null, "valor do cupom"));
			}
			if(Util.isNotNull(cupom.getPessoa()) && Util.isNotNull(cupom.getPessoa().getUsuario())
				&& Util.isNotNull(cupom.getPessoa().getUsuario().getEmail())){
				if(!usuarioRepository.existsByEmail(((Cupom) entidade).getPessoa().getUsuario().getEmail())){
					msg.append("Email não existe.");
				}
			} else {
				msg.append(validadorString.validar(null, "email"));
			}
		}
		return msg.toString();
	}
}
