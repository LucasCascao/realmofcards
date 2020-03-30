package br.com.cascao.realmofcard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.persistence.*;

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

    @OneToOne()
    @JoinColumn(name = "crr_pessoa_id")
    private Pessoa pessoa;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "carrinho_item",
            joinColumns = {@JoinColumn(name = "cri_carrinho_id")},
            inverseJoinColumns = {@JoinColumn(name = "cri_item_id")})
    private List<Item> itens;
}
