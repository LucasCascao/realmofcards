package br.edu.les.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "transicao")
public class Transicao extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsc_id")
    private Integer id;

    @Column(name = "tsc_codigo")
    private String codigo;

    @Column(name = "tsc_subtotal")
    private Double subTotal;

    @OneToMany(mappedBy = "transicao", fetch = FetchType.EAGER)
    private List<ItemTransacao> itemTransicaoList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tsc_pedido_id")
    private Pedido pedido;

    @ManyToOne()
    @JoinColumn(name = "tsc_cupom_id")
    private Cupom cupom;

    @ManyToOne()
    @JoinColumn(name = "tsc_rastreio_id")
    private Rastreio rastreio;

    @ManyToOne()
    @JoinColumn(name = "tsc_status_trasicao_id")
    private StatusTransacao statusTransacao;

    @ManyToOne()
    @JoinColumn(name = "tsc_tipo_transicao_id")
    private TipoTransicao tipoTransicao;


}
