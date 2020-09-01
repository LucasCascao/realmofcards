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
@Table(name = "telefone")
public class Telefone extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tel_id")
    private Integer id;

    @Column(name = "tel_ddd")
    private String ddd;

    @Column(name = "tel_numero")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "tel_tipo_telefone_id")
    private TipoTelefone tipoTelefone;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tel_pessoa_id")
    private Pessoa pessoa;
}
