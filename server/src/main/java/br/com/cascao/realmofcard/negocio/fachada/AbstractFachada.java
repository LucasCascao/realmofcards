package br.com.cascao.realmofcard.negocio.fachada;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.negocio.strategy.carta.CalcularPrecoVenda;
import br.com.cascao.realmofcard.negocio.strategy.carta.MoveImagem;
import br.com.cascao.realmofcard.negocio.strategy.carta.ValidaDadosCarta;
import br.com.cascao.realmofcard.negocio.strategy.cartao_credito.ValidaDadosCartaoCredito;
import br.com.cascao.realmofcard.negocio.strategy.endereco.ValidaDadosEndereco;
import br.com.cascao.realmofcard.negocio.strategy.pedido.ValidaDadosPedido;
import br.com.cascao.realmofcard.negocio.strategy.pessoa.ValidaDadosPessoa;
import br.com.cascao.realmofcard.negocio.strategy.pessoa.ValidaExistenciaPessoa;
import br.com.cascao.realmofcard.negocio.strategy.usuario.CriptografarSenha;
import br.com.cascao.realmofcard.negocio.strategy.usuario.ValidaDadosUsuario;
import br.com.cascao.realmofcard.negocio.strategy.usuario.ValidaExistenciaUsuario;
import br.com.cascao.realmofcard.negocio.strategy.usuario.ValidaSenhaUsuario;
import br.com.cascao.realmofcard.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AbstractFachada {

    protected Map<String, IPersistence> daos = new HashMap<>();

    protected Map<String, Map<String, List<IStrategy>>> regrasNegocio = new HashMap<>();;

    /*
        Todas Persistence
     */

    @Autowired
    protected PessoaPersistence pessoaPersistence;

    @Autowired
    protected UsuarioPersistence usuarioPersistence;

    @Autowired
    protected CartaPersistence cartaPersistence;

    @Autowired
    protected EnderecoPersistence enderecoPersistence;

    @Autowired
    protected CartaoCreditoPersistence cartaoCreditoPersistence;

    @Autowired
    protected PedidoPersistence pedidoPersistence;

    /*
        Todas Strategy
     */

    @Autowired
    protected ValidaDadosPessoa validaDadosPessoa;

    @Autowired
    protected ValidaExistenciaPessoa validaExistenciaPessoa;

    @Autowired
    protected ValidaDadosUsuario validaDadosUsuario;

    @Autowired
    protected ValidaExistenciaUsuario validaExistenciaUsuario;

    @Autowired
    protected CriptografarSenha criptografarSenha;

    @Autowired
    protected ValidaSenhaUsuario validaSenhaUsuario;

    @Autowired
    protected ValidaDadosCarta validaDadosCarta;

    @Autowired
    protected CalcularPrecoVenda calcularPrecoVenda;

    @Autowired
    private MoveImagem moveImagem;

    @Autowired
    protected ValidaDadosEndereco validaDadosEndereco;

    @Autowired
    protected ValidaDadosCartaoCredito validaDadosCartaoCredito;

    @Autowired
    protected ValidaDadosPedido validaDadosPedido;

    public AbstractFachada(){
    }

    protected void inicializeMaps(){

        daos.put(Pessoa.class.getName(), pessoaPersistence);
        daos.put(Usuario.class.getName(), usuarioPersistence);
        daos.put(Carta.class.getName(), cartaPersistence);
        daos.put(Endereco.class.getName(), enderecoPersistence);
        daos.put(CartaoCredito.class.getName(), cartaoCreditoPersistence);

        //------------------------ Hash Pessoa ----------------------------//

        List<IStrategy> rnsPessoaSalvar = new ArrayList<>();

        rnsPessoaSalvar.add(validaDadosPessoa);
        rnsPessoaSalvar.add(validaExistenciaPessoa);
        rnsPessoaSalvar.add(validaDadosUsuario);
        rnsPessoaSalvar.add(validaExistenciaUsuario);
        rnsPessoaSalvar.add(criptografarSenha);

        List<IStrategy> rnsPessoaAlterar = new ArrayList<>();

        rnsPessoaAlterar.add(validaDadosPessoa);
        rnsPessoaAlterar.add(validaDadosUsuario);
        rnsPessoaAlterar.add(validaExistenciaUsuario);

        Map<String,List<IStrategy>> mapaLeitor = new HashMap<>();

        mapaLeitor.put("SALVAR",rnsPessoaSalvar);
        mapaLeitor.put("ALTERAR",rnsPessoaAlterar);

        this.regrasNegocio.put(Pessoa.class.getName(), mapaLeitor);

        //------------------------ Hash Usuario ----------------------------//


        List<IStrategy> rnsUsuarioConsultar = new ArrayList<>();

        rnsUsuarioConsultar.add(validaSenhaUsuario);

        Map<String, List<IStrategy>> mapaUsuario = new HashMap<>();

        mapaUsuario.put("CONSULTAR",rnsUsuarioConsultar);

        this.regrasNegocio.put(Usuario.class.getName(), mapaUsuario);

        //------------------------ Hash Carta ----------------------------//

        List<IStrategy> rnsCartaSalvar = new ArrayList<>();

        rnsCartaSalvar.add(validaDadosCarta);
        rnsCartaSalvar.add(moveImagem);
        rnsCartaSalvar.add(calcularPrecoVenda);

        List<IStrategy> rnsCartaAlterar = new ArrayList<>();

        rnsCartaAlterar.add(validaDadosCarta);
        rnsCartaAlterar.add(moveImagem);

        Map<String, List<IStrategy>> mapaCarta = new HashMap<>();

        mapaCarta.put("SALVAR",rnsCartaSalvar);
        mapaCarta.put("ALTERAR",rnsCartaAlterar);

        this.regrasNegocio.put(Carta.class.getName(), mapaCarta);

        //----------------------- Hash Endereco --------------------------//

        List<IStrategy> rnsEnderecoSalvar = new ArrayList<>();

        rnsEnderecoSalvar.add(validaDadosEndereco);

        List<IStrategy> rnsEnderecoAlterar = new ArrayList<>();

        rnsEnderecoAlterar.add(validaDadosEndereco);

        Map<String, List<IStrategy>> mapaEndereco = new HashMap<>();

        mapaEndereco.put("SALVAR",rnsEnderecoSalvar);
        mapaEndereco.put("ALTERAR",rnsEnderecoAlterar);

        this.regrasNegocio.put(Endereco.class.getName(), mapaEndereco);

        //--------------------- Hash CartaoCredito -----------------------//

        List<IStrategy> rnsCartaoCreditoSalvar = new ArrayList<>();

        rnsCartaoCreditoSalvar.add(validaDadosCartaoCredito);

        List<IStrategy> rnsCartaoCreditoAlterar = new ArrayList<>();

        rnsCartaoCreditoAlterar.add(validaDadosCartaoCredito);

        Map<String, List<IStrategy>> mapaCartaoCredito = new HashMap<>();

        mapaCartaoCredito.put("SALVAR",rnsCartaoCreditoSalvar);
        mapaCartaoCredito.put("ALTERAR",rnsCartaoCreditoAlterar);

        this.regrasNegocio.put(CartaoCredito.class.getName(), mapaCartaoCredito);

        //------------------------ Hash Pedido --------------------------//

        List<IStrategy> rnsPedidoSalvar = new ArrayList<>();

        rnsPedidoSalvar.add(validaDadosPedido);

        List<IStrategy> rnsPedidoAlterar = new ArrayList<>();

        rnsPedidoAlterar.add(validaDadosPedido);

        Map<String, List<IStrategy>> mapaPedido = new HashMap<>();

        mapaPedido.put("SALVAR",rnsPedidoSalvar);
        mapaPedido.put("ALTERAR",rnsPedidoAlterar);

        this.regrasNegocio.put(Pedido.class.getName(), mapaPedido);
    }
}
