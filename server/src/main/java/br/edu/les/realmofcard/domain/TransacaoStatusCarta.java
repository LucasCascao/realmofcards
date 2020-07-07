package br.edu.les.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
@Entity
@Table(name = "transacao_status_carta")
public class TransacaoStatusCarta extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsc_id")
    private Integer id;

    @Column(name = "tsc_motivo")
    private String motivo;

    @ManyToOne()
    @JoinColumn(name = "tsc_carta_id")
    private Carta carta;

    @ManyToOne()
    @JoinColumn(name = "tsc_status_id")
    private Status status;
}
