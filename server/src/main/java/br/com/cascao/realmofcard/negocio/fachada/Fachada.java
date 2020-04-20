package br.com.cascao.realmofcard.negocio.fachada;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.persistence.IPersistence;

@Service
public class Fachada extends AbstractFachada implements IFachada {

    private StringBuilder sb = new StringBuilder();
    private Resultado resultado;

	private void executarRegras(EntidadeDominio entidade, List<IStrategy> rnsEntidade) {
        for (IStrategy rn : rnsEntidade) {
            String msg = rn.processar(entidade);
            if (msg != null) {
                sb.append(msg);
            }
        }
    }

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		super.inicializeMaps();
		resultado = new Resultado();
		sb.setLength(0);
		String nmClasse = entidade.getClass().getName();
		Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
		List<IStrategy> rnsEntidade = mapaEntidade.get("SALVAR");
		
		executarRegras(entidade, rnsEntidade);
		
		if(sb.length() == 0) {
			IPersistence dao = daos.get(nmClasse);
			dao.salvar(entidade);
			resultado.addEntidade(entidade);
		}else {
			resultado.addEntidade(entidade);
			resultado.setMsg(sb.toString());
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		super.inicializeMaps();
    	resultado = new Resultado();
        sb.setLength(0);
        String nmClasse = entidade.getClass().getName();

        Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
        List<IStrategy> rnsEntidade = mapaEntidade.get("ALTERAR");

        executarRegras(entidade, rnsEntidade);

        if (sb.length() == 0) {
        	IPersistence dao = daos.get(nmClasse);
            dao.alterar(entidade);
            resultado.addEntidade(entidade);
        } else {
            resultado.addEntidade(entidade);
            resultado.setMsg(sb.toString());
        }

        return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		super.inicializeMaps();
		resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();
		Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);

		if(mapaEntidade != null){
			List<IStrategy> rnsEntidade = mapaEntidade.get("EXCLUIR");
			if(rnsEntidade != null){
				executarRegras(entidade, rnsEntidade);
			}
		}

		resultado.addEntidade(entidade);

		if (sb.length() == 0) {
			IPersistence dao = daos.get(nmClasse);
			dao.excluir(entidade);
		} else {
			resultado.setMsg(sb.toString());
		}


        return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		super.inicializeMaps();
    	resultado = new Resultado();
		sb.setLength(0);
        String nmClasse = entidade.getClass().getName();
		Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);

		if(mapaEntidade != null){
			List<IStrategy> rnsEntidade = mapaEntidade.get("CONSULTAR");
			if(rnsEntidade != null){
				executarRegras(entidade, rnsEntidade);
			}
		}


		if (sb.length() == 0) {
			IPersistence dao = daos.get(nmClasse);
			resultado.setEntidades(dao.consultar(entidade));
		} else {
			resultado.addEntidade(entidade);
			resultado.setMsg(sb.toString());
		}

		return resultado;
	}

}