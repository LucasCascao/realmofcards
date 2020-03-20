package br.com.cascao.realmofcard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeDominio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id")
	private Integer id;

	@Column(name = "usu_email")
	private String email;

	@Column(name = "usu_senha")
	private String password;

	@ManyToOne()
	@JoinColumn(name = "usu_status_id")
	private Status status;

	@ManyToOne()
	@JoinColumn(name = "usu_type_user_id")
	private TipoUsuario tipoUsuario;
}
