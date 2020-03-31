package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoPersistence implements IPersistence {

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof Pedido) return pedidoRepository.save((Pedido) entidade);
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
