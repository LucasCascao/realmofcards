package br.com.cascao.realmofcard.dto;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.CategoriaCarta;
import br.com.cascao.realmofcard.domain.Jogo;
import br.com.cascao.realmofcard.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Component
public class CartaDTO {

    private Integer id;

    private String nome;

    private String descricao;

    private Double valorCompra;

    private Double precificacao;

    private Double valorVenda;

    private Integer quantidade;

    private String imagemPath;

    private Jogo jogo;

    private CategoriaCarta categoriaCarta;

    private Status status;

    private MultipartFile imagemArquivo;

    @Value("${imagem-path}")
    private String caminhoDaImagem;

    public CartaDTO transfereParaDTO(Carta carta) {

        CartaDTO cartaDTO = new CartaDTO();

        cartaDTO.id = carta.getId();
        cartaDTO.nome = carta.getNome();
        cartaDTO.descricao = carta.getDescricao();
        cartaDTO.valorCompra = carta.getValorCompra();
        cartaDTO.precificacao = carta.getPrecificacao();
        cartaDTO.valorVenda = carta.getValorVenda();
        cartaDTO.jogo = carta.getJogo();
        cartaDTO.status = carta.getStatus();
        cartaDTO.quantidade = carta.getQuantidade();
        cartaDTO.categoriaCarta = carta.getCategoriaCarta();


        return cartaDTO;
    }

    public Carta tranfereParaCarta(CartaDTO cartaDTO) {

        Carta carta = new Carta();

        carta.setId(cartaDTO.id);
        carta.setNome(cartaDTO.nome);
        carta.setDescricao(cartaDTO.descricao);
        carta.setValorCompra(cartaDTO.valorCompra);
        carta.setPrecificacao(cartaDTO.precificacao);
        carta.setValorVenda(cartaDTO.valorVenda);
        carta.setJogo(cartaDTO.jogo);
        carta.setStatus(cartaDTO.status);
        carta.setQuantidade(cartaDTO.quantidade);
        carta.setCategoriaCarta(cartaDTO.categoriaCarta);
        carta.setImagemPath(caminhoDaImagem + cartaDTO.imagemArquivo.getOriginalFilename());

        return carta;
    }
}

