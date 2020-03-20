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
@Table(name = "endereco")
public class Endereco extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Integer id;

    @Column(name = "end_logradouro")
    private String logradouro;

    @Column(name = "end_numero")
    private String numero;

    @Column(name = "end_bairro")
    private String bairro;

    @Column(name = "end_cep")
    private String cep;

    @Column(name = "end_comentario")
    private String comentario;

    @ManyToOne()
    @JoinColumn(name = "end_cidade_id")
    private Cidade cidade;

    @ManyToOne()
    @JoinColumn(name = "end_usuario_id")
    private Usuario usuario;
}
