package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.repository.FormaPagamentoRepository;
import br.com.cascao.realmofcard.repository.PedidoRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PedidoPersistence implements IPersistence {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CartaRepository cartaRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;
            pedido.getItemList().forEach(item -> cartaRepository.save(item.getCarta()));
            pedido.setDataCompra(LocalDate.now());
            Set<FormaPagamento> formaPagamentoList = new HashSet<>();
            pedido.getFormaPagamentoList().forEach(formaPagamento -> formaPagamentoList.add(formaPagamentoRepository.save(formaPagamento)));
            pedido.setFormaPagamentoList(formaPagamentoList);
            pedido = pedidoRepository.save((Pedido) entidade);
            Carrinho carrinho = carrinhoRepository.findByPessoa_Id(pedido.getCliente().getId());
            carrinho.setItemList(new ArrayList<>());
            carrinhoRepository.save(carrinho);
            return pedido;
        }
        else return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;
            for (Item item : pedido.getItemList()) {
                item.setCarta(cartaRepository.save(item.getCarta()));
            }
            pedido = pedidoRepository.save(pedido);
        }
        else entidade = null;
    }

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        List<EntidadeDominio> pedidos = new ArrayList<>();

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;

            if(pedido.getId() != null){
                pedidos.add(pedidoRepository.findById(pedido.getId()).get());
                return pedidos;
            }

            if(Util.isNotNull(pedido.getStatusPedido())){
                pedidos.addAll(pedidoRepository.findByStatusPedido_Id(pedido.getStatusPedido().getId()));
                return pedidos;
            }

            if(Util.isNotNull(pedido.getCliente())
                && Util.isNotNull(pedido.getCliente().getId())){
                pedidos.addAll(pedidoRepository.findByCliente_Id(pedido.getCliente().getId()));
                return pedidos;
            }

            pedidoRepository.findAll().forEach(pedidoResultado -> pedidos.add(pedidoResultado));

            return pedidos;

        } else return null;
    }
}
