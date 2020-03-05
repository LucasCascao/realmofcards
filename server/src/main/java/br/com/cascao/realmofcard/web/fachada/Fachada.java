package br.com.cascao.realmofcard.web.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cascao.realmofcard.dao.IDAO;
import br.com.cascao.realmofcard.dao.PessoaDAO;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.negocio.pessoa.ValidaDadosPessoa;
import org.springframework.stereotype.Component;

@Component
public class Fachada implements IFachada{
	
	private Map<String, IDAO> daos;

    // Mapa Macro, com TODAS as regras de negócio
    // Observe: ele ó um mapa de um mapa. O mapa que está dentro possui para cada chave uma lista de strategy
    private Map<String, Map<String, List<IStrategy>>> regrasNegocio;
    private StringBuilder sb = new StringBuilder();
    private Resultado resultado;
	
	public Fachada() {
		// Instanciando mapas de daos e regras de neg�cio macro
		daos = new HashMap<String, IDAO>();
		
		// Instanciando o mapa macro;
		regrasNegocio = new HashMap<String, Map<String,List<IStrategy>>>();
		
		// Instanciando o mapa de DAOs;
		daos.put(Pessoa.class.getName(), new PessoaDAO());

		//------------------------ Hash Pessoa ----------------------------//
		
		//Criando as regras de negócio de Pessoa
		
		ValidaDadosPessoa validarDadosPessoa = new ValidaDadosPessoa();
		
		//Criando a lista para guardar as régras de negocios de salvar Pessoa
		
		List<IStrategy> rnsPessoaSalvar = new ArrayList<IStrategy>();
		
		//Atribuindo a regra de negócio para a lista
		
		rnsPessoaSalvar.add(validarDadosPessoa);
		
		//Criando a lista para guardar as régras de negocios de salvar Pessoa
		
		List<IStrategy> rnsPessoaAlterar = new ArrayList<IStrategy>();
		
		//Atribuindo a regra de negócio para a lista
		
		rnsPessoaAlterar.add(validarDadosPessoa);
		
		//Criando mapa que represente todas as regras da entidade Pessoa
		
		Map<String,List<IStrategy>> mapaLeitor = new HashMap<String,List<IStrategy>>();
		
		//Atribuindo as listas de IStrategy aos seus respectivos comandos
		
		mapaLeitor.put("SALVAR",rnsPessoaSalvar);
		mapaLeitor.put("ALTERAR",rnsPessoaAlterar);
		
		//Atribuindo o mapa completo da entidade Pessoa no mapa geral
		
		regrasNegocio.put(Pessoa.class.getName(), mapaLeitor);
		
		//---------------------------- Fim das Regras de Neg�cio -----------------------------//
	}
	
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
		
		resultado = new Resultado();
		sb.setLength(0);
		String nmClasse = entidade.getClass().getName();
		System.out.println(nmClasse);
		Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
		List<IStrategy> rnsEntidade = mapaEntidade.get("SALVAR");
		
		executarRegras(entidade, rnsEntidade);
		
		if(sb.length() == 0) {
			IDAO dao = daos.get(nmClasse);
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
		resultado = new Resultado();
        sb.setLength(0);
        String nmClasse = entidade.getClass().getName();

        Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
        List<IStrategy> rnsEntidade = mapaEntidade.get("ALTERAR");

        executarRegras(entidade, rnsEntidade);

        if (sb.length() == 0) {
            IDAO dao = daos.get(nmClasse);
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
		resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        IDAO dao = daos.get(nmClasse);
        resultado.addEntidade(entidade);
        dao.excluir(entidade);

        return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        IDAO dao = daos.get(nmClasse);
        resultado.setEntidades(dao.consultar(entidade));

        return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        IDAO dao = daos.get(nmClasse);
        resultado.setEntidades(dao.consultar(entidade));
        return resultado;
	}

}