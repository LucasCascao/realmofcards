package br.edu.les.realmofcard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "troca")
public class Troca extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trc_id")
    private Integer id;

    @Column(name = "trc_subtotal")
    private Double subTotal;

    @OneToMany(mappedBy = "troca", fetch = FetchType.EAGER)
    private List<ItemTroca> itemListParaTroca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trc_pedido_id")
    private Pedido pedidoParaTroca;

    @ManyToOne()
    @JoinColumn(name = "trc_cupom_id")
    private Cupom cupom;

    @ManyToOne()
    @JoinColumn(name = "trc_rastreio_id")
    private Rastreio rastreio;
}
