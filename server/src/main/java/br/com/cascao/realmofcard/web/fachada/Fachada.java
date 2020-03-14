package br.com.cascao.realmofcard.web.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cascao.realmofcard.negocio.usuario.ValidaDadosUsuario;
import br.com.cascao.realmofcard.negocio.usuario.ValidaExistenciaUsuario;
import br.com.cascao.realmofcard.service.AutenticaService;
import br.com.cascao.realmofcard.service.UsuarioService;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.pessoa.ValidaExistenciaPessoa;
import br.com.cascao.realmofcard.negocio.usuario.ValidaSenhaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cascao.realmofcard.service.PessoaService;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.negocio.pessoa.ValidaDadosPessoa;
import br.com.cascao.realmofcard.service.IService;

@Service
public class Fachada implements IFachada {

	private Map<String, IService> daos;

    private Map<String, Map<String, List<IStrategy>>> regrasNegocio;
    private StringBuilder sb = new StringBuilder();
    private Resultado resultado;
    
    @Autowired
	public Fachada(PessoaService pessoaService,
				   UsuarioService usuarioService,
				   ValidaDadosPessoa validaDadosPessoa,
				   ValidaExistenciaPessoa validaExistenciaPessoa,
				   ValidaDadosUsuario validaDadosUsuario,
				   ValidaExistenciaUsuario validaExistenciaUsuario) {

    	daos = new HashMap<String, IService>();
		
		regrasNegocio = new HashMap<String, Map<String,List<IStrategy>>>();

		daos.put(Pessoa.class.getName(), pessoaService);
		daos.put(Usuario.class.getName(), usuarioService);

		//------------------------ Hash Pessoa ----------------------------//
		
		List<IStrategy> rnsPessoaSalvar = new ArrayList<IStrategy>();

		rnsPessoaSalvar.add(validaDadosPessoa);
		rnsPessoaSalvar.add(validaExistenciaPessoa);

		List<IStrategy> rnsPessoaAlterar = new ArrayList<IStrategy>();

		rnsPessoaAlterar.add(validaDadosPessoa);
		
		Map<String,List<IStrategy>> mapaLeitor = new HashMap<String,List<IStrategy>>();
		
		mapaLeitor.put("SALVAR",rnsPessoaSalvar);
		mapaLeitor.put("ALTERAR",rnsPessoaAlterar);
		
		regrasNegocio.put(Pessoa.class.getName(), mapaLeitor);

		//------------------------ Hash Usuario ----------------------------//


		List<IStrategy> rnsUsuarioSalvar = new ArrayList<IStrategy>();

		rnsUsuarioSalvar.add(validaExistenciaUsuario);
		rnsUsuarioSalvar.add(validaDadosUsuario);

		Map<String, List<IStrategy>> mapaUsuario = new HashMap<String, List<IStrategy>>();

		mapaUsuario.put("SALVAR",rnsUsuarioSalvar);

		regrasNegocio.put(Usuario.class.getName(), mapaUsuario);

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
		Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
		List<IStrategy> rnsEntidade = mapaEntidade.get("SALVAR");
		
		executarRegras(entidade, rnsEntidade);
		
		if(sb.length() == 0) {
			IService dao = daos.get(nmClasse);
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
        	IService dao = daos.get(nmClasse);
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

        IService dao = daos.get(nmClasse);
        resultado.addEntidade(entidade);
        dao.excluir(entidade);

        return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();
        
        IService dao = daos.get(nmClasse);
        resultado.setEntidades(dao.consultar(entidade));

        return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        IService dao = daos.get(nmClasse);
        resultado.setEntidades(dao.consultar(entidade));
        return resultado;
	}

}