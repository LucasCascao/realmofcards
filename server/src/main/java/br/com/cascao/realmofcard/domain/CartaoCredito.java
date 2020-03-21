package br.com.cascao.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "cartao")
public class CartaoCredito extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crt_id")
    private Integer id;

    @Column(name = "crt_numero")
    private String numero;

    @Column(name = "crt_codigo_seguranca")
    private String codigoSeguranca;

    @ManyToOne()
    @JoinColumn(name = "crt_bandeira_id")
    private Bandeira bandeira;

    @ManyToOne()
    @JoinColumn(name = "crt_pessoa_id")
    private Pessoa pessoa;
}
