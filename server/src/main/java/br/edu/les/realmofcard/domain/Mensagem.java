package br.edu.les.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class Mensagem extends EntidadeDominio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private StringBuilder mensagem;
	
	private String assunto;
	
	public void adiciona(String mensagem) {
		this.mensagem.append(mensagem);
	}
	
	public void limpa() {
		this.mensagem.delete(0, this.mensagem.length()-1);
	}
}
