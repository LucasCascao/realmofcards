package br.edu.les.realmofcard.domain.grafico;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class Serie extends EntidadeDominio {
	
	private String name;
	private List<Object> data;

}
