package br.com.cascao.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.persistence.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item")
public class Item extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id")
    private Integer id;

    @Column(name = "itm_quantidade")
    private Integer quantidade;

    @ManyToOne()
    @JoinColumn(name = "itm_carta_id")
    private Carta carta;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "item_pedido",
                joinColumns = {@JoinColumn(name = "itp_item_id")},
                inverseJoinColumns = {@JoinColumn(name = "itp_pedido_id")})
    private List<Pedido> pedidos;

}
