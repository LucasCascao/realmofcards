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
@Table(name = "status_carta")
public class StatusCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scr_id ")
    private Integer id;

    @Column(name = "scr_status")
    private String status;
}
