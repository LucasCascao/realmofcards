package br.edu.les.realmofcard.facade;

import br.edu.les.realmofcard.domain.grafico.Dashboard;
import br.edu.les.realmofcard.strategy.telefone.ValidaDadosTelefone;
import br.edu.les.realmofcard.strategy.transacao_status_carta.AltararStatusCarta;
import br.edu.les.realmofcard.strategy.transacao_status_carta.ValidaDadosTransacaoStatusCarta;
import br.edu.les.realmofcard.strategy.carrinho.*;
import br.edu.les.realmofcard.strategy.carta.*;
import br.edu.les.realmofcard.strategy.cupom.*;
import br.edu.les.realmofcard.strategy.dashboard.ValidaDadosDashboard;
import br.edu.les.realmofcard.strategy.devolucao.EnviaEmailStatusDaDevolucao;
import br.edu.les.realmofcard.strategy.grupo_precificacao.ValidaDadosGrupoPrecificacao;
import br.edu.les.realmofcard.strategy.pedido.*;
import br.edu.les.realmofcard.strategy.transicao.CalculaValorTransicao;
import br.edu.les.realmofcard.strategy.transicao.GeraCodigoTransacaoTransicao;
import br.edu.les.realmofcard.strategy.transicao.RetiraQuantidadeItemDoPedido;
import br.edu.les.realmofcard.strategy.transicao.RetornaQuantidadeItemPedidoParaEstoque;
import br.edu.les.realmofcard.strategy.troca.*;
import br.edu.les.realmofcard.strategy.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.cartao_credito.ValidaDadosCartaoCredito;
import br.edu.les.realmofcard.strategy.cartao_credito.ValidaDataValidadeCartao;
import br.edu.les.realmofcard.strategy.cartao_credito.ValidaNumeroJaExiste;
import br.edu.les.realmofcard.strategy.endereco.ValidaDadosEndereco;
import br.edu.les.realmofcard.strategy.endereco.ValidaExistenciaCidade;
import br.edu.les.realmofcard.strategy.pessoa.ValidaDadosPessoa;
import br.edu.les.realmofcard.strategy.pessoa.ValidaExistenciaPessoa;
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
    private TipoTelefoneDAO tipoTelefoneDAO;

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
    private TransacaoDAO transacaoDAO;

    @Autowired
    private CupomDAO cupomDAO;

    @Autowired
    private DashboardDAO dashboardDAO;

    @Autowired
    private TransacaoStatusCartaDAO transacaoStatusCartaDAO;

    @Autowired
    private GrupoPrecificacaoDAO grupoPrecificacaoDAO;

    /*
        Todas Strategy
     */

    @Autowired
    private ValidaDadosPessoa validaDadosPessoa;

    @Autowired
    private ValidaDadosTelefone validaDadosTelefone;

    @Autowired
    private ValidaExistenciaPessoa validaExistenciaPessoa;

    @Autowired
    private ValidaDadosUsuario validaDadosUsuario;

    @Autowired
    private ValidaSenhasIguais validaSenhasIguais;

    @Autowired
    private ValidaExistenciaUsuario validaExistenciaUsuario;

    @Autowired
    private ValidaUsuarioAtivo validaUsuarioAtivo;

    @Autowired
    private GeraCodigoUsuario geraCodigoUsuario;

    @Autowired
    private CriptografaSenha criptografarSenha;

    @Autowired
    private ValidaSenhaUsuario validaSenhaUsuario;

    @Autowired
    private ValidaDadosCarta validaDadosCarta;

    @Autowired
    private GeraCodigoCarta geraCodigoCarta;

    @Autowired
    private ValidaQuantidadeEstoque validaQuantidadeEstoque;

    @Autowired
    private  RetiraCartaNaoDisponivelDoCarrinho retiraCartaNaoDisponivelDoCarrinho;

    @Autowired
    private ValidaDadosTransacaoStatusCarta validaDadosTransacaoStatusCarta;

    @Autowired
    private InsereDataCadastro insereDataCadastro;

    @Autowired
    private AltararStatusCarta altararStatusCarta;

    @Autowired
    private ValidaDadosGrupoPrecificacao validaDadosGrupoPrecificacao;

    @Autowired
    private MoveImagem moveImagem;

    @Autowired
    private InativaCartaNaoDisponivel inativaCartaNaoDisponivel;

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
    private AtualizaItensPedidos atualizaItensPedidos;

    @Autowired
    private MudaStatusCupom mudaStatusCupom;

    @Autowired
    private EnviaEmailStatusDoPedido enviaEmailStatusDoPedido;

    @Autowired
    private EnviaEmailStatusDaDevolucao enviaEmailStatusDaDevolucao;

    @Autowired
    private ValidaDadosTroca validaDadosTroca;

    @Autowired
    private RetiraQuantidadeItemDoPedido retiraQuantidadeItemDoPedido;

    @Autowired
    private RetornaQuantidadeItemPedidoParaEstoque retornaQuantidadeItemPedidoParaEstoque;

    @Autowired
    private CalculaValorTransicao calculaValorTransicao;

    @Autowired
    private EnviaEmailStatusDaTroca enviaEmailStatusDaTroca;

    @Autowired
    private ValidaDadosCupom validaDadosCupom;

    @Autowired
    private GeraCodigoCupom geraCodigoCupom;

    @Autowired
    private ValidaDadosCupomConsulta validaDadosCupomConsulta;

    @Autowired
    private ValidaCupomAtivo validaCupomAtivo;

    @Autowired
    private ValidaExistenciaCupom validaExistenciaCupom;

    @Autowired
    private GeraCupomComValorRestante geraCupomComValorRestante;

    @Autowired
    private GeraCodigoTransacaoTransicao geraCodigoTransacaoTransicao;

    @Autowired
    private ValidaDadosDashboard validaDadosDashboard;


    public AbstractFachada(){
    }

    protected void inicializeMaps(){

        //------------------ Hash Classe e DAO -------------------------//

        daos.put(Pessoa.class.getName(), pessoaDAO);
        daos.put(Usuario.class.getName(), usuarioDAO);
        daos.put(TipoTelefone.class.getName(), tipoTelefoneDAO);
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
        daos.put(Transicao.class.getName(), transacaoDAO);
        daos.put(Cupom.class.getName(), cupomDAO);
        daos.put(Dashboard.class.getName(), dashboardDAO);
        daos.put(TransacaoStatusCarta.class.getName(), transacaoStatusCartaDAO);
        daos.put(GrupoPrecificacao.class.getName(), grupoPrecificacaoDAO);

        //------------------------ Hash Pessoa ----------------------------//

        List<IStrategy> rnsPessoaSalvar = new ArrayList<>();

        rnsPessoaSalvar.add(validaDadosPessoa);
        rnsPessoaSalvar.add(validaDadosTelefone);
        rnsPessoaSalvar.add(validaExistenciaPessoa);
        rnsPessoaSalvar.add(validaDadosUsuario);
        rnsPessoaSalvar.add(validaSenhasIguais);
        rnsPessoaSalvar.add(validaExistenciaUsuario);
        rnsPessoaSalvar.add(geraCodigoUsuario);
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
        rnsUsuarioConsultar.add(validaUsuarioAtivo);

        List<IStrategy> rnsUsuarioAlterar = new ArrayList<>();

        rnsUsuarioAlterar.add(validaSenhasIguais);
        rnsUsuarioAlterar.add(validaDadosUsuario);
        rnsUsuarioAlterar.add(criptografarSenha);

        Map<String, List<IStrategy>> mapaUsuario = new HashMap<>();

        mapaUsuario.put("CONSULTAR",rnsUsuarioConsultar);
        mapaUsuario.put("ALTERAR",rnsUsuarioAlterar);

        this.regrasNegocio.put(Usuario.class.getName(), mapaUsuario);

        //------------------------ Hash Carta ----------------------------//

        List<IStrategy> rnsCartaSalvar = new ArrayList<>();

        rnsCartaSalvar.add(validaDadosCarta);
        rnsCartaSalvar.add(moveImagem);
        rnsCartaSalvar.add(inseriItemDisponivelParaEstoque);
        rnsCartaSalvar.add(geraCodigoCarta);
        rnsCartaSalvar.add(insereDataCadastro);

        List<IStrategy> rnsCartaAlterar = new ArrayList<>();

        rnsCartaAlterar.add(validaDadosCarta);
        rnsCartaAlterar.add(moveImagem);
        rnsCartaAlterar.add(validaQuantidadeEstoque);
        rnsCartaAlterar.add(inativaCartaNaoDisponivel);
        rnsCartaAlterar.add(retiraCartaNaoDisponivelDoCarrinho);

        Map<String, List<IStrategy>> mapaCarta = new HashMap<>();

        mapaCarta.put("SALVAR",rnsCartaSalvar);
        mapaCarta.put("ALTERAR",rnsCartaAlterar);

        this.regrasNegocio.put(Carta.class.getName(), mapaCarta);

        //----------------- Hash TransacaoStatusCarta --------------------//

        List<IStrategy> rnsTransacaoStatusCartaSalvar = new ArrayList<>();

        rnsTransacaoStatusCartaSalvar.add(validaDadosTransacaoStatusCarta);
        rnsTransacaoStatusCartaSalvar.add(altararStatusCarta);

        List<IStrategy> rnsTransacaoStatusCartaAlterar = new ArrayList<>();

        rnsTransacaoStatusCartaAlterar.add(validaDadosTransacaoStatusCarta);

        Map<String, List<IStrategy>> mapaTransacaoStatusCarta = new HashMap<>();

        mapaTransacaoStatusCarta.put("SALVAR", rnsTransacaoStatusCartaSalvar);
        mapaTransacaoStatusCarta.put("ALTERAR", rnsTransacaoStatusCartaAlterar);

        this.regrasNegocio.put(TransacaoStatusCarta.class.getName(), mapaTransacaoStatusCarta);

        //------------------ Hash GrupoPrecificacao ----------------------//

        List<IStrategy> rnsGrupoPrecificacaoSalvar = new ArrayList<>();
        rnsGrupoPrecificacaoSalvar.add(validaDadosGrupoPrecificacao);

        List<IStrategy> rnsGrupoPrecificacaoAlterar = new ArrayList<>();
        rnsGrupoPrecificacaoAlterar.add(validaDadosGrupoPrecificacao);

        Map<String, List<IStrategy>> mapaGrupoPrecificacao = new HashMap<>();

        mapaGrupoPrecificacao.put("SALVAR", rnsGrupoPrecificacaoSalvar);
        mapaGrupoPrecificacao.put("ALTERAR", rnsGrupoPrecificacaoAlterar);

        this.regrasNegocio.put(GrupoPrecificacao.class.getName(), mapaGrupoPrecificacao);

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
        rnsPedidoSalvar.add(geraCupomComValorRestante);
        rnsPedidoSalvar.add(mudaStatusCupom);
        rnsPedidoSalvar.add(enviaEmailStatusDoPedido);

        List<IStrategy> rnsPedidoAlterar = new ArrayList<>();

        rnsPedidoAlterar.add(validaDadosPedido);
        rnsPedidoAlterar.add(retornaItemEstoque);
        rnsPedidoAlterar.add(enviaEmailStatusDoPedido);

        Map<String, List<IStrategy>> mapaPedido = new HashMap<>();

        mapaPedido.put("SALVAR",rnsPedidoSalvar);
        mapaPedido.put("ALTERAR",rnsPedidoAlterar);

        this.regrasNegocio.put(Pedido.class.getName(), mapaPedido);

        //------------------------ Hash Troca --------------------------//

        List<IStrategy> rnsTrocaSalvar = new ArrayList<>();

        rnsTrocaSalvar.add(validaDadosTroca);
        rnsTrocaSalvar.add(calculaValorTransicao);
        rnsTrocaSalvar.add(geraCodigoTransacaoTransicao);
        rnsTrocaSalvar.add(enviaEmailStatusDaTroca);
        rnsTrocaSalvar.add(retiraQuantidadeItemDoPedido);
        rnsTrocaSalvar.add(enviaEmailStatusDaDevolucao);

        List<IStrategy> rnsTrocaAlterar = new ArrayList<>();

        rnsTrocaAlterar.add(retiraQuantidadeItemDoPedido);
        rnsTrocaAlterar.add(retornaQuantidadeItemPedidoParaEstoque);
        rnsTrocaAlterar.add(enviaEmailStatusDaTroca);
        rnsTrocaAlterar.add(enviaEmailStatusDaDevolucao);

        Map<String, List<IStrategy>> mapaTroca = new HashMap<>();

        mapaTroca.put("SALVAR", rnsTrocaSalvar);
        mapaTroca.put("ALTERAR", rnsTrocaAlterar);

        regrasNegocio.put(Transicao.class.getName(), mapaTroca);

        //------------------------ Hash Cupom --------------------------//

        List<IStrategy> rnsCupomSalvar = new ArrayList<>();

        rnsCupomSalvar.add(geraCodigoCupom);
        rnsCupomSalvar.add(validaDadosCupom);

        List<IStrategy> rnsCupomConsultar = new ArrayList<>();

        rnsCupomConsultar.add(validaDadosCupomConsulta);
        rnsCupomConsultar.add(validaExistenciaCupom);
        rnsCupomConsultar.add(validaCupomAtivo);

        Map<String, List<IStrategy>> mapaCupom = new HashMap<>();

        mapaCupom.put("SALVAR", rnsCupomSalvar);
        mapaCupom.put("CONSULTAR", rnsCupomConsultar);

        regrasNegocio.put(Cupom.class.getName(), mapaCupom);

        //------------------------ Hash Dashboard ------------------------//

        List<IStrategy> rnsDashboardConsultar = new ArrayList<>();

        rnsDashboardConsultar.add(validaDadosDashboard);

        Map<String, List<IStrategy>> mapaDashboard = new HashMap<>();

        mapaDashboard.put("CONSULTAR", rnsDashboardConsultar);

        regrasNegocio.put(Dashboard.class.getName(), mapaDashboard);
    }
}
