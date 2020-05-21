package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.StatusPedido;
import br.edu.les.realmofcard.repository.PedidoRepository;
import br.edu.les.realmofcard.repository.StatusPedidoRepository;

import java.util.List;

@Component
public class MudaStatusPedido implements IStrategy {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private StatusPedidoRepository statusPedidoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;

            if(pedido.getStatusPedido() != null && pedido.getStatusPedido().getId() != null){

                pedido.setStatusPedido(pedidoRepository.findById(pedido.getId()).get().getStatusPedido());

                List<StatusPedido> statusPedidoList = (List<StatusPedido>) statusPedidoRepository.findAll();

                if(pedido.getStatusPedido().getStatus() == "Em Processamento"){
                    pedido.setStatusPedido(statusPedidoList.get(2));
                }

                if(pedido.getStatusPedido().getStatus() == "Em Transito"){
                    pedido.setStatusPedido(statusPedidoList.get(3));
                }

                if(pedido.getStatusPedido().getStatus() == "Entregue"){
                    pedido.setStatusPedido(statusPedidoList.get(4));
                }

                if(pedido.getStatusPedido().getStatus() == "Solicitação de Troca em Andamento"){
                    pedido.setStatusPedido(statusPedidoList.get(5));
                }

                if(pedido.getStatusPedido().getStatus() == "Aguardando Recebimento"){
                    pedido.setStatusPedido(statusPedidoList.get(6));
                }

            }
        }

        return msg.toString();
    }
}
