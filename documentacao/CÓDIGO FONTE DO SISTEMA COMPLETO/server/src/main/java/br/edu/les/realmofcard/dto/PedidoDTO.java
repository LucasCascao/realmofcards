package br.edu.les.realmofcard.dto;

import br.edu.les.realmofcard.domain.*;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class PedidoDTO extends EntidadeDominio implements IDTO {

    private Integer id;

    private LocalDate dataCompra;

    private LocalDate dataEstimada;

    private Double valorTotal;

    private String codigoPedido;

    private Double valorFrete;

    private Pessoa cliente;

    private StatusPedido statusPedido;

    private Endereco endereco;

    private Rastreio rastreio;

    private Set<FormaPagamentoDTO> formaPagamentoList;

    private Set<Item> itemList;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        Pedido pedido = (Pedido) dominio;

        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setCodigoPedido(pedido.getCodigoPedido());
        pedidoDTO.setDataCompra(pedido.getDataCompra());
        pedidoDTO.setDataEstimada(pedido.getDataEstimada());
        pedidoDTO.setEndereco(pedido.getEndereco());
        pedidoDTO.setValorFrete(pedido.getValorFrete());
        pedidoDTO.setRastreio(pedido.getRastreio());
        pedidoDTO.setItemList(pedido.getItemList());
        pedidoDTO.setValorTotal(pedido.getValorTotal());
        pedidoDTO.setCliente(pedido.getCliente());
        pedidoDTO.setStatusPedido(pedido.getStatusPedido());

        Set<FormaPagamentoDTO> formaPagamentoDTOList = new HashSet<>();
        for (FormaPagamento formaPagamento : pedido.getFormaPagamentoList()) {
            formaPagamentoDTOList.add( (FormaPagamentoDTO) new FormaPagamentoDTO().parseEntityToDTO(formaPagamento));
        }

        pedidoDTO.setFormaPagamentoList(formaPagamentoDTOList);


        return pedidoDTO;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
