package br.com.cascao.realmofcard.negocio.fachada;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.negocio.strategy.carrinho.ValidaDadosCarrinho;
import br.com.cascao.realmofcard.negocio.strategy.carta.CalcularPrecoVenda;
import br.com.cascao.realmofcard.negocio.strategy.carta.MoveImagem;
import br.com.cascao.realmofcard.negocio.strategy.carta.ValidaDadosCarta;
import br.com.cascao.realmofcard.negocio.strategy.cartao_credito.ValidaDadosCartaoCredito;
import br.com.cascao.realmofcard.negocio.strategy.endereco.ValidaDadosEndereco;
import br.com.cascao.realmofcard.negocio.strategy.endereco.ValidaExistenciaCidade;
import br.com.cascao.realmofcard.negocio.strategy.pedido.ValidaDadosPedido;
import br.com.cascao.realmofcard.negocio.strategy.pessoa.ValidaDadosPessoa;
import br.com.cascao.realmofcard.negocio.strategy.pessoa.ValidaExistenciaPessoa;
import br.com.cascao.realmofcard.negocio.strategy.usuario.CriptografaSenha;
import br.com.cascao.realmofcard.negocio.strategy.usuario.ValidaDadosUsuario;
import br.com.cascao.realmofcard.negocio.strategy.usuario.ValidaExistenciaUsuario;
import br.com.cascao.realmofcard.negocio.strategy.usuario.ValidaSenhaUsuario;
import br.com.cascao.realmofcard.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    protected CartegoriaCartaPersistence cartegoriaCartaPersistence;

    @Autowired
    protected EstadoPersistence estadoPersistence;

    @Autowired
    protected CidadePersistence cidadePersistence;

    @Autowired
    protected CarrinhoPersistence carrinhoPersistence;


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
    protected CriptografaSenha criptografarSenha;

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
    protected ValidaExistenciaCidade validaExistenciaCidade;

    @Autowired
    protected ValidaDadosCartaoCredito validaDadosCartaoCredito;

    @Autowired
    protected ValidaDadosPedido validaDadosPedido;

    @Autowired
    protected ValidaDadosCarrinho validaDadosCarrinho;

    public AbstractFachada(){
    }

    protected void inicializeMaps(){

        daos.put(Pessoa.class.getName(), pessoaPersistence);
        daos.put(Usuario.class.getName(), usuarioPersistence);
        daos.put(Carta.class.getName(), cartaPersistence);
        daos.put(Endereco.class.getName(), enderecoPersistence);
        daos.put(CartaoCredito.class.getName(), cartaoCreditoPersistence);
        daos.put(Pedido.class.getName(), pedidoPersistence);
        daos.put(CategoriaCarta.class.getName(), cartegoriaCartaPersistence);
        daos.put(Estado.class.getName(), estadoPersistence);
        daos.put(Cidade.class.getName(), cidadePersistence);
        daos.put(Carrinho.class.getName(), carrinhoPersistence);

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

        rnsUsuarioConsultar.add(validaDadosUsuario);
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
        rnsEnderecoSalvar.add(validaExistenciaCidade);

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

        //------------------------ Hash Categoria --------------------------//

        Map<String, List<IStrategy>> mapaCategoria = new HashMap<>();

        regrasNegocio.put(CategoriaCarta.class.getName(), mapaCategoria);

        //------------------------ Hash Categoria --------------------------//

        List<IStrategy> rnsCarrinhoSalvar = new ArrayList<>();

        rnsCarrinhoSalvar.add(validaDadosCarrinho);

        List<IStrategy> rnsCarrinhoAlterar = new ArrayList<>();

        rnsCarrinhoAlterar.add(validaDadosCarrinho);

        Map<String, List<IStrategy>> mapaCarrinho = new HashMap<>();

        mapaCarrinho.put("SALVAR", rnsCarrinhoSalvar);
        mapaCarrinho.put("ALTERAR", rnsCarrinhoAlterar);

        this.regrasNegocio.put(Carrinho.class.getName(), mapaCarrinho);

    }
}
