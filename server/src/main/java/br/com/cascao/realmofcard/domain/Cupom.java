package br.com.cascao.realmofcard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
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
    @Column(name = "cup_id")
    private Integer id;
    
    @Column(name = "cup_codigo")
    private String codigo;

    @Column(name = "cup_valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "cup_status_id")
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "cup_troca_id")
    private Troca troca;
}
