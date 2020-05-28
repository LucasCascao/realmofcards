package br.edu.les.realmofcard.domain;

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
    
    @Column(name = "fpa_valor_pagamento")
    private Double valorPagamento;

    @ManyToOne()
    @JoinColumn(name = "fpa_cupom_id")
    private Cupom cupom;

    @ManyToOne()
    @JoinColumn(name = "fpa_cartao_credito_id")
    private CartaoCredito cartaoCredito;
}
