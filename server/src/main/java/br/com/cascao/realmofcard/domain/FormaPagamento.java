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
    
    @Column(name = "fpa_valor_pagamento")
    private Double valorPagamento;

    @Column(name = "fpa_registro_cartao")
    private String registroCartao;
}
