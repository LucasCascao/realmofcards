package br.com.cascao.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento extends EntidadeDominio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fpa_id")
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "forma_pagamento_cartao",
            joinColumns = {@JoinColumn(name = "fpc_forma_pagamento_id")},
            inverseJoinColumns = {@JoinColumn(name = "fpc_cartao_credito_id")})
    private List<CartaoCredito> cartaoCreditoList;
}
