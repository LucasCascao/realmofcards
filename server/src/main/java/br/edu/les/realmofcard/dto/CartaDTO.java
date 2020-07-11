package br.edu.les.realmofcard.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.edu.les.realmofcard.domain.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Component
public class CartaDTO extends EntidadeDominio implements IDTO {

    private Integer id;

    private String nome;

    private String descricao;

    private LocalDate dataCadastro;

    private Double valorVenda;

    private GrupoPrecificacao grupoPrecificacao;

    private Integer quantidadeDisponivel;

    private Integer quantidadeEstoque;

    private String imagemPath;

    private Jogo jogo;

    private CategoriaCarta categoriaCarta;

    private Status status;

    private MultipartFile imagemArquivo;

    @Value("${imagem-path}")
    private String caminhoDaImagem;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof Carta){

            Carta carta = (Carta) dominio;
            CartaDTO cartaDTO = new CartaDTO();

            cartaDTO.setId(carta.getId());
            cartaDTO.setNome(carta.getNome());
            cartaDTO.setDescricao(carta.getDescricao());
            cartaDTO.setDataCadastro(carta.getDataCadastro());
            cartaDTO.setValorVenda(carta.getValorCompra() * carta.getGrupoPrecificacao().getValor());
            cartaDTO.setJogo(carta.getJogo());
            cartaDTO.setQuantidadeDisponivel(carta.getQuantidadeDisponivel());
            cartaDTO.setQuantidadeEstoque(carta.getQuantidadeEstoque());
            cartaDTO.setCategoriaCarta(carta.getCategoriaCarta());

            return cartaDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {

        if(dto instanceof CartaDTO){

            CartaDTO cartaDTO = (CartaDTO) dto;
            Carta carta = new Carta();

            carta.setId(cartaDTO.id);
            carta.setNome(cartaDTO.nome);
            carta.setDescricao(cartaDTO.descricao);
            carta.setJogo(cartaDTO.jogo);
            carta.setStatus(cartaDTO.status);
            carta.setQuantidadeDisponivel(cartaDTO.quantidadeDisponivel);
            carta.setCategoriaCarta(cartaDTO.categoriaCarta);
            carta.setImagemPath(caminhoDaImagem + cartaDTO.imagemArquivo.getOriginalFilename());

            return carta;
        }
        return null;
    }
}

