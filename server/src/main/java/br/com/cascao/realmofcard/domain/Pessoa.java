package br.com.cascao.realmofcard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
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

	private String nome;

	private String sexo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pes_id")
	private Integer id;

	@Column(name = "pes_nome")
	private String firstName;

	@Column(name = "pes_sobrenome")
	private String lastName;

	@Column(name = "pes_data_nascimento")
	private Date dataNascimento;

	@Column(name = "pes_sexo")
	private String sex;

	@Column(name = "pes_cpf")
	private String cpf;

	@Column(name = "pes_email")
	private String email;

	@Column(name = "pes_nome_usuario")
	private String username;

	@Column(name = "pes_senha")
	private String password;
}
