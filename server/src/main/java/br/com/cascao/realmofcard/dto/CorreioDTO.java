package br.com.cascao.realmofcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import br.com.cascao.realmofcard.domain.EntidadeDominio;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class CorreioDTO extends EntidadeDominio{
    private Double valorCusto;
    private Integer quantidadeDiasEntrega;
}
