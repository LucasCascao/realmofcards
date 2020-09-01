package br.edu.les.realmofcard.domain.grafico;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class Dashboard extends EntidadeDominio {
	
	private String title;
	private List<Serie> series;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String tipoGrafico;

}
