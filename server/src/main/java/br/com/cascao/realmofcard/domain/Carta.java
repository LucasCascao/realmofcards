package br.com.cascao.realmofcard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "carta")
public class Carta extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer id;

    @Column(name = "car_nome")
    private String nome;

    @Column(name = "car_descricao")
    private String descricao;

    @Column(name = "car_valor_compra")
    private Double valorCompra;

    @Column(name = "car_precificacao")
    private Double precificacao;

    @Column(name = "car_valor_venda")
    private Double valorVenda;

    @Column(name = "car_quantidade_disponivel")
    private Integer quantidadeDisponivel;

    @Column(name = "car_quantidade_estoque")
    private Integer quantidadeEstoque;

    @Column(name = "car_imagem_path")
    private String imagemPath;

    @ManyToOne()
    @JoinColumn(name = "car_jogo_id")
    private Jogo jogo;

    @ManyToOne()
    @JoinColumn(name = "car_categoria_id")
    private CategoriaCarta categoriaCarta;

    @ManyToOne
    @JoinColumn(name = "car_status_id")
    private Status status;
}
