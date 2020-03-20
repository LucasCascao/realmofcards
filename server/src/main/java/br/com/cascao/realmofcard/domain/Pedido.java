package br.com.cascao.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private Integer id;

    @Column(name = "ped_data_compra")
    private LocalDate dataCompra;

    @OneToOne()
    @JoinColumn(name = "ped_cliente_id")
    private Usuario cliente;

    @OneToOne()
    @JoinColumn(name = "ped_administrador_id")
    private Usuario administrador;

    @OneToOne()
    @JoinColumn(name = "ped_status_pedido_id")
    private StatusPedido statusPedido;
}
