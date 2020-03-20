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
@Table(name = "carta")
public class Carta extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer id;

    @Column(name = "car_nome")
    private String nome;

    @Column(name = "car_descricao")
    private String descricao;

    @Column(name = "car_valor")
    private Double valor;

    @ManyToOne()
    @JoinColumn(name = "car_jogo_id")
    private Jogo jogo;

    @ManyToOne()
    @JoinColumn(name = "car_categoria_id")
    private CategoriaCarta categoriaCarta;

    @ManyToOne
    @JoinColumn(name = "car_status_id")
    private Status status;
}
