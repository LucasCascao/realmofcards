package br.com.cascao.realmofcard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@Entity
@Table(name = "pessoa")
public class Pessoa extends EntidadeDominio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pes_id")
	private Integer id;

	@Column(name = "pes_nome")
	private String nome;

	@Column(name = "pes_sobrenome")
	private String sobrenome;

	@JsonFormat(pattern="yyyy-MM-dd")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "pes_data_nascimento")
	private LocalDate dataNascimento;

	@Column(name = "pes_sexo")
	private String sexo;

	@Column(name = "pes_cpf")
	private String cpf;

	@JoinColumn(name = "pes_usuario_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

}
