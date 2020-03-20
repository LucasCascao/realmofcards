package br.com.cascao.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipd_id")
    private Integer id;

    @Column(name = "ipd_quantidade")
    private Integer quantidade;

    @ManyToOne()
    @JoinColumn(name = "ipd_carta_id")
    private Carta carta;

    @OneToOne()
    @JoinColumn(name = "ipd_pedido_id")
    private Pedido pedido;

}
