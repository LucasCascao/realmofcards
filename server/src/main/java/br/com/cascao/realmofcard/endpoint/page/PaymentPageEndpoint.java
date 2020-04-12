package br.com.cascao.realmofcard.endpoint.page;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.dto.CarrinhoDTO;
import br.com.cascao.realmofcard.dto.CartaoCreditoDTO;
import br.com.cascao.realmofcard.dto.EnderecoDTO;
import br.com.cascao.realmofcard.dto.page.PaymentPageDTO;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/paymentpage")
public class PaymentPageEndpoint {

    @Autowired
    private Resultado resultado;

    @Autowired
    private Fachada fachada;

    @Autowired
    private EnderecoDTO enderecoDTO;

    @Autowired
    private CartaoCreditoDTO cartaoCreditoDTO;

    @Autowired
    private CarrinhoDTO carrinhoDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody PaymentPageDTO paymentPageDTO){

        Pessoa pessoa = paymentPageDTO.getPessoa();
        Endereco endereco = Endereco.builder().pessoa(pessoa).preferido(true).build();
        CartaoCredito cartaoCredito = CartaoCredito.builder().pessoa(pessoa).preferido(true).build();
        Carrinho carrinho = Carrinho.builder().pessoa(pessoa).build();
        List<CartaoCreditoDTO> cartaoCreditoList = new ArrayList<>();

        fachada.consultar(endereco).getEntidades().forEach( enderecoResultado ->
            paymentPageDTO.setEndereco((EnderecoDTO) enderecoDTO.parseEntityToDTO(enderecoResultado))
        );

        fachada.consultar(cartaoCredito).getEntidades().forEach( cartaoCreditoResultado ->
            cartaoCreditoList.add((CartaoCreditoDTO) cartaoCreditoDTO.parseEntityToDTO(cartaoCreditoResultado))
        );

        paymentPageDTO.setCartaoCreditoList(cartaoCreditoList);

        fachada.consultar(carrinho).getEntidades().forEach( carrinhoResultado -> {
            paymentPageDTO.setCarrinho((CarrinhoDTO)carrinhoDTO.parseEntityToDTO(carrinhoResultado));
        });

        resultado.addEntidade(paymentPageDTO);

        return ResponseEntity.ok().body(resultado);
    }
}
