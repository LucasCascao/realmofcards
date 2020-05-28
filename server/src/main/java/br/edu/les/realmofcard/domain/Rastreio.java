package br.edu.les.realmofcard.domain;

import lombok.*;
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
@Table(name = "rastreio")
public class Rastreio extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rto_id")
    private Integer id;

    @Column(name = "rto_codigo_rastreio")
    private String codigoRastreio;
}
