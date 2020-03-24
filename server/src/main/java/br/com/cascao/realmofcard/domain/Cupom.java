package br.com.cascao.realmofcard.domain;

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
@Table(name = "cupom")
public class Cupom extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ban_id")
    private Integer id;

    @Column(name = "cup_valor")
    private Double valor;

    @Column(name = "cup_status")
    private Boolean status;

    @Column(name = "cup_codigo")
    private String codigo;
}
