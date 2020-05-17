package br.edu.les.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "status_pedido")
public class StatusPedido extends EntidadeDominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spd_id")
    private Integer id;

    @Column(name = "spd_status")
    private String status;
}
