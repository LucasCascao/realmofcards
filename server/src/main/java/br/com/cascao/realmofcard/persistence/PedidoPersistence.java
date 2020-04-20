package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.FormaPagamento;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.FormaPagamentoRepository;
import br.com.cascao.realmofcard.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoPersistence implements IPersistence {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;
            pedido.setDataCompra(LocalDate.now());
            pedido.setFormaPagamento(formaPagamentoRepository.save(pedido.getFormaPagamento()));
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
        if(entidade instanceof Pedido) entidade = pedidoRepository.save((Pedido) entidade);
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
            pedidoRepository.findByCliente_Id(pedido.getCliente().getId())
                    .forEach( resultadoPedido -> pedidos.add(resultadoPedido));
            return pedidos;
        } else return null;
    }
}
