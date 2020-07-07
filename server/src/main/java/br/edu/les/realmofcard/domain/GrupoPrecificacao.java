package br.edu.les.realmofcard.domain;

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
@Table(name = "grupo_precificacao")
public class GrupoPrecificacao extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpr_id")
    private Integer id;

    @Column(name = "gpr_nome")
    private String nome;

    @Column(name = "gpr_valor_percentual")
    private Double valor;
}
