package br.edu.les.realmofcard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
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

//	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "pes_data_nascimento")
	private LocalDate dataNascimento;

	@Size(max = 1)
	@Column(name = "pes_sexo")
	private String sexo;

	@Size(max = 11)
	@Column(name = "pes_cpf")
	private String cpf;

	@JoinColumn(name = "pes_usuario_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa" ,cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa" ,cascade = CascadeType.ALL)
	private List<CartaoCredito> cartoesCredito = new ArrayList<>();

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa" ,cascade = CascadeType.ALL)
	private List<Carrinho> carrinhos = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "pessoa" ,cascade = CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<>();

}
