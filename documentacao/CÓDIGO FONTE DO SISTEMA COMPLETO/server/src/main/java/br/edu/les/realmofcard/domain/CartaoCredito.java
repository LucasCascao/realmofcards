package br.edu.les.realmofcard.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "cartao_credito")
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

    @Column(name = "crt_vencimento_ano")
    private String vencimentoAno;

    @Column(name = "crt_vencimento_mes")
    private String vencimentoMes;

    @Column(name = "crt_titular_nome")
    private String titularNome;

    @Column(name = "crt_cpf_titular")
    private String cpfTitular;

    @ManyToOne()
    @JoinColumn(name = "crt_bandeira_id")
    private Bandeira bandeira;

    @ManyToOne()
    @JoinColumn(name = "crt_pessoa_id")
    private Pessoa pessoa;

    @ManyToOne()
    @JoinColumn(name = "crt_status_id")
    private Status status;
}
