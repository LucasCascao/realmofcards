package br.edu.les.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//extends EntidadeAplicacao
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Resultado {

	private String msg;
	private List< EntidadeDominio > entidades;
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	public void addEntidade(EntidadeDominio entidade) {
        if(entidades == null) {
                entidades = new ArrayList<EntidadeDominio>();
        }
        entidades.add(entidade);
	}
}
