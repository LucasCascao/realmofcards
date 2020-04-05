package br.com.cascao.realmofcard.negocio.fachada;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.negocio.strategy.carrinho.*;
import br.com.cascao.realmofcard.negocio.strategy.carta.CalcularPrecoVenda;
import br.com.cascao.realmofcard.negocio.strategy.carta.MoveImagem;
import br.com.cascao.realmofcard.negocio.strategy.carta.ValidaDadosCarta;
import br.com.cascao.realmofcard.negocio.strategy.cartao_credito.ValidaDadosCartaoCredito;
import br.com.cascao.realmofcard.negocio.strategy.endereco.ValidaDadosEndereco;
import br.com.cascao.realmofcard.negocio.strategy.endereco.ValidaExistenciaCidade;
import br.com.cascao.realmofcard.negocio.strategy.pedido.ValidaDadosPedido;
import br.com.cascao.realmofcard.negocio.strategy.pessoa.ValidaDadosPessoa;
import br.com.cascao.realmofcard.negocio.strategy.pessoa.ValidaExistenciaPessoa;
import br.com.cascao.realmofcard.negocio.strategy.usuario.*;
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
    private PessoaPersistence pessoaPersistence;

    @Autowired
    private UsuarioPersistence usuarioPersistence;

    @Autowired
    private CartaPersistence cartaPersistence;

    @Autowired
    private EnderecoPersistence enderecoPersistence;

    @Autowired
    private CartaoCreditoPersistence cartaoCreditoPersistence;

    @Autowired
    private PedidoPersistence pedidoPersistence;

    @Autowired
    private CartegoriaCartaPersistence cartegoriaCartaPersistence;

    @Autowired
    private EstadoPersistence estadoPersistence;

    @Autowired
    private CidadePersistence cidadePersistence;

    @Autowired
    private CarrinhoPersistence carrinhoPersistence;


    /*
        Todas Strategy
     */

    @Autowired
    private ValidaDadosPessoa validaDadosPessoa;

    @Autowired
    private ValidaExistenciaPessoa validaExistenciaPessoa;

    @Autowired
    private ValidaDadosUsuario validaDadosUsuario;

    @Autowired
    private ValidaSenhasIguais validaSenhasIguais;

    @Autowired
    private ValidaExistenciaUsuario validaExistenciaUsuario;

    @Autowired
    private CriptografaSenha criptografarSenha;

    @Autowired
    private ValidaSenhaUsuario validaSenhaUsuario;

    @Autowired
    private ValidaDadosCarta validaDadosCarta;

    @Autowired
    private CalcularPrecoVenda calcularPrecoVenda;

    @Autowired
    private MoveImagem moveImagem;

    @Autowired
    private ValidaDadosEndereco validaDadosEndereco;

    @Autowired
    private ValidaExistenciaCidade validaExistenciaCidade;

    @Autowired
    private ValidaDadosCartaoCredito validaDadosCartaoCredito;

    @Autowired
    private ValidaDadosPedido validaDadosPedido;

    @Autowired
    private ValidaDadosCarrinho validaDadosCarrinho;

    @Autowired
    private VerificaCarrinhoAtivo verificaCarrinhoAtivo;

    @Autowired
    private VerificaProdutoInativoNoCarrinho verificaProdutoInativoNoCarrinho;

    @Autowired
    private PegaCarrinhoSeExistir pegaCarrinhoSeExistir;

    @Autowired
    private ValidaItemJaEstaNoCarrinho validaItemJaEstaNoCarrinho;

    @Autowired
    private ValidaQuantidadeItemEstoque validaQuantidadeItemEstoque;


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
        rnsPessoaSalvar.add(validaSenhasIguais);
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
        rnsCarrinhoSalvar.add(validaQuantidadeItemEstoque);
        rnsCarrinhoSalvar.add(validaItemJaEstaNoCarrinho);
        rnsCarrinhoSalvar.add(verificaCarrinhoAtivo);
        rnsCarrinhoSalvar.add(pegaCarrinhoSeExistir);

        List<IStrategy> rnsCarrinhoAlterar = new ArrayList<>();

        rnsCarrinhoAlterar.add(validaDadosCarrinho);
        rnsCarrinhoAlterar.add(validaQuantidadeItemEstoque);
        rnsCarrinhoAlterar.add(validaItemJaEstaNoCarrinho);
        rnsCarrinhoAlterar.add(verificaCarrinhoAtivo);

        List<IStrategy> rnsCarrinhoConsultar = new ArrayList<>();

        rnsCarrinhoConsultar.add(verificaProdutoInativoNoCarrinho);

        Map<String, List<IStrategy>> mapaCarrinho = new HashMap<>();

        mapaCarrinho.put("SALVAR", rnsCarrinhoSalvar);
        mapaCarrinho.put("ALTERAR", rnsCarrinhoAlterar);
        mapaCarrinho.put("CONSULTAR", rnsCarrinhoConsultar);

        this.regrasNegocio.put(Carrinho.class.getName(), mapaCarrinho);

    }
}
