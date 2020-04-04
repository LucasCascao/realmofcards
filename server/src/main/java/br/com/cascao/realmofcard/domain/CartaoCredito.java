package br.com.cascao.realmofcard.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Builder
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

    @Size(max = 16)
    @Column(name = "crt_numero", unique = true)
    private String numero;

    @Size(max = 3)
    @Column(name = "crt_codigo_seguranca")
    private String codigoSeguranca;

    @Column(name = "crt_preferido")
    private Boolean preferido;

    @ManyToOne()
    @JoinColumn(name = "crt_bandeira_id")
    private Bandeira bandeira;

    @ManyToOne()
    @JoinColumn(name = "crt_pessoa_id")
    private Pessoa pessoa;
}
