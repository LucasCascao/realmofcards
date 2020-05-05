package br.com.cascao.realmofcard.domain;

import java.time.LocalDate;
import java.util.List;

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

    @OneToOne()
    @JoinColumn(name = "ped_cliente_id")
    private Pessoa cliente;

    @OneToOne()
    @JoinColumn(name = "ped_administrador_id")
    private Pessoa administrador;

    @OneToOne()
    @JoinColumn(name = "ped_status_pedido_id")
    private StatusPedido statusPedido;

    @Column(name = "ped_endereco")
    private String enderecoEscolhido;

    @Transient
    private Endereco endereco;

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(name = "forma_pagamento_pedido",
            joinColumns = {@JoinColumn(name = "fpp_pedido_id")},
            inverseJoinColumns = {@JoinColumn(name = "fpp_forma_pagamento_id")})
    private List<FormaPagamento> formaPagamentoList;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "item_pedido",
            joinColumns = {@JoinColumn(name = "itp_pedido_id")},
            inverseJoinColumns = {@JoinColumn(name = "itp_item_id")})
    private List<Item> itemList;
}
