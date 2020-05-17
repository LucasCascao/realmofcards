package br.edu.les.realmofcard.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
@Entity
@Table(name = "carrinho")
public class Carrinho extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crr_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "crr_pessoa_id")
    private Pessoa pessoa;

    @ManyToMany()
    @JoinTable(name = "carrinho_item",
            joinColumns = {@JoinColumn(name = "cri_carrinho_id")},
            inverseJoinColumns = {@JoinColumn(name = "cri_item_id")})
    private List<Item> itemList;
}
