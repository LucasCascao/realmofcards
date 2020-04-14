package br.com.cascao.realmofcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class CorreioDTO {
    private Double valorCusto;
    private Integer quantidadeDiasEntrega;
}
