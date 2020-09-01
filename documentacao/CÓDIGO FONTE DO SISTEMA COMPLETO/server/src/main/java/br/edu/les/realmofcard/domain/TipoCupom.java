package br.edu.les.realmofcard.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tipo_cupom")
public class TipoCupom extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tcp_id")
    private Integer id;


    @Column(name = "tcp_tipo_nome")
    private String tipoNome;

}
