package br.com.cascao.realmofcard.web.fachada;

import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.negocio.pessoa.ValidaDadosPessoa;
import br.com.cascao.realmofcard.negocio.pessoa.ValidaExistenciaPessoa;
import br.com.cascao.realmofcard.negocio.usuario.ValidaDadosUsuario;
import br.com.cascao.realmofcard.negocio.usuario.ValidaExistenciaUsuario;
import br.com.cascao.realmofcard.negocio.usuario.ValidaSenhaUsuario;
import br.com.cascao.realmofcard.service.IService;
import br.com.cascao.realmofcard.service.PessoaService;
import br.com.cascao.realmofcard.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AbstractFachada {

    protected Map<String, IService> daos = new HashMap<>();

    protected Map<String, Map<String, List<IStrategy>>> regrasNegocio = new HashMap<>();;

    @Autowired
    protected PessoaService pessoaService;

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected ValidaDadosPessoa validaDadosPessoa;

    @Autowired
    protected ValidaExistenciaPessoa validaExistenciaPessoa;

    @Autowired
    protected ValidaDadosUsuario validaDadosUsuario;

    @Autowired
    protected ValidaExistenciaUsuario validaExistenciaUsuario;

    @Autowired
    protected ValidaSenhaUsuario validaSenhaUsuario;

    public AbstractFachada(){
    }

    protected void inicializeIStrategy(){

        daos.put(Pessoa.class.getName(), pessoaService);
        daos.put(Usuario.class.getName(), usuarioService);

        //------------------------ Hash Pessoa ----------------------------//

        List<IStrategy> rnsPessoaSalvar = new ArrayList<IStrategy>();

        rnsPessoaSalvar.add(validaDadosPessoa);
        rnsPessoaSalvar.add(validaExistenciaPessoa);
        rnsPessoaSalvar.add(validaDadosUsuario);
        rnsPessoaSalvar.add(validaExistenciaUsuario);

        List<IStrategy> rnsPessoaAlterar = new ArrayList<IStrategy>();

        rnsPessoaAlterar.add(validaDadosPessoa);
        rnsPessoaAlterar.add(validaDadosUsuario);
        rnsPessoaAlterar.add(validaExistenciaUsuario);

        Map<String,List<IStrategy>> mapaLeitor = new HashMap<String,List<IStrategy>>();

        mapaLeitor.put("SALVAR",rnsPessoaSalvar);
        mapaLeitor.put("ALTERAR",rnsPessoaAlterar);

        this.regrasNegocio.put(Pessoa.class.getName(), mapaLeitor);

        //------------------------ Hash Usuario ----------------------------//


        List<IStrategy> rnsUsuarioConsultar = new ArrayList<>();

        rnsUsuarioConsultar.add(validaSenhaUsuario);

        Map<String, List<IStrategy>> mapaUsuario = new HashMap<>();

        mapaUsuario.put("CONSULTAR",rnsUsuarioConsultar);

        this.regrasNegocio.put(Usuario.class.getName(), mapaUsuario);
    }
}
