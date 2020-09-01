package br.edu.les.realmofcard.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private Integer id;

    @Column(name = "ped_data_compra")
    private LocalDate dataCompra;

    @Column(name = "ped_data_estimada")
    private LocalDate dataEstimada;

    @Column(name = "ped_valor_total")
    private Double valorTotal;

    @Column(name = "ped_codigo_pedido")
    private String codigoPedido;

    @Column(name = "ped_valor_frete")
    private Double valorFrete;

    @OneToOne()
    @JoinColumn(name = "ped_cliente_id")
    private Pessoa cliente;

    @OneToOne()
    @JoinColumn(name = "ped_administrador_id")
    private Pessoa administrador;

    @OneToOne()
    @JoinColumn(name = "ped_status_pedido_id")
    private StatusPedido statusPedido;

    @ManyToOne()
    @JoinColumn(name = "ped_endereco_id")
    private Endereco endereco;

    @ManyToOne()
    @JoinColumn(name = "ped_rastreio_id")
    private Rastreio rastreio;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "forma_pagamento_pedido",
            joinColumns = {@JoinColumn(name = "fpp_pedido_id")},
            inverseJoinColumns = {@JoinColumn(name = "fpp_forma_pagamento_id")})
    private Set<FormaPagamento> formaPagamentoList;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "item_pedido",
            joinColumns = {@JoinColumn(name = "itp_pedido_id")},
            inverseJoinColumns = {@JoinColumn(name = "itp_item_id")})
    private Set<Item> itemList;
}
