package br.edu.les.realmofcard.facade;

import br.edu.les.realmofcard.strategy.carrinho.*;
import br.edu.les.realmofcard.strategy.cupom.ValidaCupomAtivo;
import br.edu.les.realmofcard.strategy.cupom.ValidaDadosCupom;
import br.edu.les.realmofcard.strategy.cupom.ValidaExistenciaCupom;
import br.edu.les.realmofcard.strategy.pedido.*;
import br.edu.les.realmofcard.strategy.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.carta.CalcularPrecoVenda;
import br.edu.les.realmofcard.strategy.carta.InseriItemDisponivelParaEstoque;
import br.edu.les.realmofcard.strategy.carta.MoveImagem;
import br.edu.les.realmofcard.strategy.carta.ValidaDadosCarta;
import br.edu.les.realmofcard.strategy.cartao_credito.ValidaDadosCartaoCredito;
import br.edu.les.realmofcard.strategy.cartao_credito.ValidaDataValidadeCartao;
import br.edu.les.realmofcard.strategy.cartao_credito.ValidaNumeroJaExiste;
import br.edu.les.realmofcard.strategy.endereco.ValidaDadosEndereco;
import br.edu.les.realmofcard.strategy.endereco.ValidaExistenciaCidade;
import br.edu.les.realmofcard.strategy.pessoa.ValidaDadosPessoa;
import br.edu.les.realmofcard.strategy.pessoa.ValidaExistenciaPessoa;
import br.edu.les.realmofcard.strategy.troca.GeraCodigoRastreio;
import br.edu.les.realmofcard.strategy.troca.RetiraQuantidadeItemDoPedido;
import br.edu.les.realmofcard.strategy.troca.RetornaQuantidadeItemPedidoParaEstoque;
import br.edu.les.realmofcard.strategy.troca.ValidaDadosTroca;
import br.edu.les.realmofcard.dao.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AbstractFachada {

    protected Map<String, IDAO> daos = new HashMap<>();

    protected Map<String, Map<String, List<IStrategy>>> regrasNegocio = new HashMap<>();;

    /*
        Todas Persistence
     */

    @Autowired
    private PessoaDAO pessoaDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private CartaDAO cartaDAO;

    @Autowired
    private EnderecoDAO enderecoDAO;

    @Autowired
    private CartaoCreditoDAO cartaoCreditoDAO;

    @Autowired
    private BandeiraDAO bandeiraDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private CartegoriaCartaDAO cartegoriaCartaDAO;

    @Autowired
    private EstadoDAO estadoDAO;

    @Autowired
    private CidadeDAO cidadeDAO;

    @Autowired
    private CarrinhoDAO carrinhoDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private TrocaDAO trocaDAO;

    @Autowired
    private CupomDAO cupomDAO;


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
    private InseriItemDisponivelParaEstoque inseriItemDisponivelParaEstoque;

    @Autowired
    private ValidaDadosEndereco validaDadosEndereco;

    @Autowired
    private ValidaExistenciaCidade validaExistenciaCidade;

    @Autowired
    private ValidaDadosCartaoCredito validaDadosCartaoCredito;

    @Autowired
    private ValidaDataValidadeCartao validaDataValidadeCartao;

    @Autowired
    private ValidaNumeroJaExiste validaNumeroJaExiste;

    @Autowired
    private ValidaDadosPedido validaDadosPedido;

    @Autowired
    private CalculaValorPedido calculaValorPedido;

    @Autowired
    private ValidaDadosCarrinho validaDadosCarrinho;

    @Autowired
    private VerificaProdutoInativoNoCarrinho verificaProdutoInativoNoCarrinho;

    @Autowired
    private PegaCarrinhoSeExistir pegaCarrinhoSeExistir;

    @Autowired
    private ValidaItemJaEstaNoCarrinho validaItemJaEstaNoCarrinho;

    @Autowired
    private ValidaQuantidadeItemDisponivel validaQuantidadeItemDisponivel;

    @Autowired
    private RetiraItemDisponivel retiraItemDisponivel;

    @Autowired
    private RetornaItemDisponivel retornaItemDisponivel;

    @Autowired
    private GeraCodigoPedido geraCodigoPedido;

    @Autowired
    private CalcularDataEntrega calcularDataEntrega;

    @Autowired
    private RetiraItemEstoque retiraItemEstoque;

    @Autowired
    private RetornaItemEstoque retornaItemEstoque;

    @Autowired
    private InsereEnderecoEscolhido insereEnderecoEscolhido;

    @Autowired
    private AtualizaItensPedidos atualizaItensPedidos;

    @Autowired
    private ValidaDadosTroca validaDadosTroca;

    @Autowired
    private RetiraQuantidadeItemDoPedido retiraQuantidadeItemDoPedido;

    @Autowired
    private RetornaQuantidadeItemPedidoParaEstoque retornaQuantidadeItemPedidoParaEstoque;

    @Autowired
    private GeraCodigoRastreio geraCodigoRastreio;

    @Autowired
    private ValidaDadosCupom validaDadosCupom;

    @Autowired
    private ValidaCupomAtivo validaCupomAtivo;

    @Autowired
    private ValidaExistenciaCupom validaExistenciaCupom;


    public AbstractFachada(){
    }

    protected void inicializeMaps(){

        //------------------ Hash Classe e DAO -------------------------//

        daos.put(Pessoa.class.getName(), pessoaDAO);
        daos.put(Usuario.class.getName(), usuarioDAO);
        daos.put(Carta.class.getName(), cartaDAO);
        daos.put(Endereco.class.getName(), enderecoDAO);
        daos.put(CartaoCredito.class.getName(), cartaoCreditoDAO);
        daos.put(Pedido.class.getName(), pedidoDAO);
        daos.put(CategoriaCarta.class.getName(), cartegoriaCartaDAO);
        daos.put(Estado.class.getName(), estadoDAO);
        daos.put(Cidade.class.getName(), cidadeDAO);
        daos.put(Carrinho.class.getName(), carrinhoDAO);
        daos.put(Item.class.getName(), itemDAO);
        daos.put(Bandeira.class.getName(), bandeiraDAO);
        daos.put(Troca.class.getName(), trocaDAO);
        daos.put(Cupom.class.getName(), cupomDAO);


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

        List<IStrategy> rnsUsuarioAlterar = new ArrayList<>();

        rnsUsuarioAlterar.add(validaDadosUsuario);
        rnsUsuarioAlterar.add(validaSenhaUsuario);
        rnsUsuarioAlterar.add(validaSenhasIguais);

        Map<String, List<IStrategy>> mapaUsuario = new HashMap<>();

        mapaUsuario.put("CONSULTAR",rnsUsuarioConsultar);

        this.regrasNegocio.put(Usuario.class.getName(), mapaUsuario);

        //------------------------ Hash Carta ----------------------------//

        List<IStrategy> rnsCartaSalvar = new ArrayList<>();

        rnsCartaSalvar.add(validaDadosCarta);
        rnsCartaSalvar.add(moveImagem);
        rnsCartaSalvar.add(calcularPrecoVenda);
        rnsCartaSalvar.add(inseriItemDisponivelParaEstoque);

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
        rnsCartaoCreditoSalvar.add(validaDataValidadeCartao);
        rnsCartaoCreditoSalvar.add(validaNumeroJaExiste);

        Map<String, List<IStrategy>> mapaCartaoCredito = new HashMap<>();

        mapaCartaoCredito.put("SALVAR",rnsCartaoCreditoSalvar);

        this.regrasNegocio.put(CartaoCredito.class.getName(), mapaCartaoCredito);

        //------------------------ Hash Categoria --------------------------//

        Map<String, List<IStrategy>> mapaCategoria = new HashMap<>();

        regrasNegocio.put(CategoriaCarta.class.getName(), mapaCategoria);

        //------------------------ Hash Item --------------------------//

        List<IStrategy> rnsItemAlterar = new ArrayList<>();

        rnsItemAlterar.add(validaQuantidadeItemDisponivel);
        rnsItemAlterar.add(retiraItemDisponivel);

        List<IStrategy> rnsItemExcluir = new ArrayList<>();

        rnsItemExcluir.add(retornaItemDisponivel);

        Map<String, List<IStrategy>> mapaItem = new HashMap<>();

        mapaItem.put("EXCLUIR", rnsItemExcluir);

        this.regrasNegocio.put(Item.class.getName(), mapaItem);

        //------------------------ Hash Carrinho --------------------------//

        List<IStrategy> rnsCarrinhoSalvar = new ArrayList<>();

        rnsCarrinhoSalvar.add(validaDadosCarrinho);
        rnsCarrinhoSalvar.add(validaItemJaEstaNoCarrinho);
        rnsCarrinhoSalvar.add(pegaCarrinhoSeExistir);
        rnsCarrinhoSalvar.add(validaQuantidadeItemDisponivel);
        rnsCarrinhoSalvar.add(retiraItemDisponivel);

        List<IStrategy> rnsCarrinhoAlterar = new ArrayList<>();

        rnsCarrinhoAlterar.add(validaDadosCarrinho);
        rnsCarrinhoAlterar.add(validaQuantidadeItemDisponivel);
        rnsCarrinhoAlterar.add(retiraItemDisponivel);

        List<IStrategy> rnsCarrinhoConsultar = new ArrayList<>();

        rnsCarrinhoConsultar.add(verificaProdutoInativoNoCarrinho);

        Map<String, List<IStrategy>> mapaCarrinho = new HashMap<>();

        mapaCarrinho.put("SALVAR", rnsCarrinhoSalvar);
        mapaCarrinho.put("ALTERAR", rnsCarrinhoAlterar);
        mapaCarrinho.put("CONSULTAR", rnsCarrinhoConsultar);

        this.regrasNegocio.put(Carrinho.class.getName(), mapaCarrinho);

        //------------------------ Hash Pedido --------------------------//

        List<IStrategy> rnsPedidoSalvar = new ArrayList<>();

        rnsPedidoSalvar.add(validaDadosPedido);
        rnsPedidoSalvar.add(atualizaItensPedidos);
        rnsPedidoSalvar.add(calculaValorPedido);
        rnsPedidoSalvar.add(geraCodigoPedido);
        rnsPedidoSalvar.add(calcularDataEntrega);
        rnsPedidoSalvar.add(retiraItemEstoque);
        rnsPedidoSalvar.add(insereEnderecoEscolhido);

        List<IStrategy> rnsPedidoAlterar = new ArrayList<>();

        rnsPedidoAlterar.add(validaDadosPedido);
        rnsPedidoAlterar.add(retornaItemEstoque);

        Map<String, List<IStrategy>> mapaPedido = new HashMap<>();

        mapaPedido.put("SALVAR",rnsPedidoSalvar);
        mapaPedido.put("ALTERAR",rnsPedidoAlterar);

        this.regrasNegocio.put(Pedido.class.getName(), mapaPedido);

        //------------------------ Hash Troca --------------------------//

        List<IStrategy> rnsTrocaSalvar = new ArrayList<>();

        rnsTrocaSalvar.add(validaDadosTroca);
        rnsTrocaSalvar.add(retiraQuantidadeItemDoPedido);

        List<IStrategy> rnsTrocaAlterar = new ArrayList<>();

        rnsTrocaAlterar.add(retiraQuantidadeItemDoPedido);
        rnsTrocaAlterar.add(retornaQuantidadeItemPedidoParaEstoque);
        rnsTrocaAlterar.add(geraCodigoRastreio);

        Map<String, List<IStrategy>> mapaTroca = new HashMap<>();

        mapaTroca.put("SALVAR", rnsTrocaSalvar);
        mapaTroca.put("ALTERAR", rnsTrocaAlterar);

        regrasNegocio.put(Troca.class.getName(), mapaTroca);

        //------------------------ Hash Cupom --------------------------//

        List<IStrategy> rnsCupomConsultar = new ArrayList<>();

        rnsCupomConsultar.add(validaDadosCupom);
        rnsCupomConsultar.add(validaExistenciaCupom);
        rnsCupomConsultar.add(validaCupomAtivo);

        Map<String, List<IStrategy>> mapaCupom = new HashMap<>();

        mapaCupom.put("CONSULTAR", rnsCupomConsultar);

        regrasNegocio.put(Cupom.class.getName(), mapaCupom);
    }
}
