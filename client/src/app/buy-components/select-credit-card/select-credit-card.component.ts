import { Component, OnInit } from '@angular/core';
import {UtilService} from "../../../services/util.service";
import {CartaoCredito} from "../../../model/domain/cartao-credito.model";

@Component({
  selector: 'app-select-credit-card',
  templateUrl: './select-credit-card.component.html',
  styleUrls: ['./select-credit-card.component.css']
})
export class SelectCreditCardComponent implements OnInit {

  constructor(private service: UtilService) { }

  cartoes: CartaoCredito[];

  idCartaoSelecionado: number;

  ngOnInit(): void {

    // tslint:disable-next-line:radix max-line-length
    this.idCartaoSelecionado = sessionStorage.getItem('cartaoSelecionado') != null ? Number.parseInt(sessionStorage.getItem('idCartaoSelecionado')) : null;

    const cartaoCredito = new CartaoCredito();
    const pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'))
    cartaoCredito.pessoa = pessoa;

    const cartoesSelecionados = new Array<CartaoCredito>();

    sessionStorage.setItem('cartoesSelecionados', JSON.stringify(cartoesSelecionados));

    this.getCartoes(cartaoCredito);
  }

  getCartoes(cartaoCredito: CartaoCredito){
    this.service.get(cartaoCredito, 'cartaocredito').subscribe( resultado => {
      this.cartoes = resultado?.entidades;
    });
  }

  selecionaCartao(cartaoSelecionado: CartaoCredito) {

    const cartoesSelecionados: Array<CartaoCredito> = JSON.parse(sessionStorage.get('cartoesSelecionados'));

    if (cartoesSelecionados.length < 2){
      cartoesSelecionados.push(cartaoSelecionado);
      sessionStorage.setItem('cartoesSelecionados', JSON.stringify(cartoesSelecionados));
    }
  }

}
