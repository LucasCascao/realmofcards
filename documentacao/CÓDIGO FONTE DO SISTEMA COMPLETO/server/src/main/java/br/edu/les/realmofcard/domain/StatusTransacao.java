package br.edu.les.realmofcard.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "status_transicao")
public class StatusTransacao extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stt_id")
    private Integer id;

    @Column(name = "stt_status_nome")
    private String statusNome;
}
