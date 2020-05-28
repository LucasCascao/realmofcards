package br.edu.les.realmofcard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "item_troca")
public class ItemTroca extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itc_id")
    private Integer id;

    @Column(name = "itc_quantidade")
    private Integer quantidade;

    @ManyToOne()
    @JoinColumn(name = "itc_item_id")
    private Item itemParaTroca;

    @JsonIgnore()
    @ManyToOne()
    @JoinColumn(name = "itc_troca_id")
    private Troca troca;
}
