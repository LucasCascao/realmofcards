import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {CartaoCredito} from '../../../model/domain/cartao-credito.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-select-credit-card',
  templateUrl: './select-credit-card.component.html',
  styleUrls: ['./select-credit-card.component.css']
})
export class SelectCreditCardComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router) { }

  cartoes: CartaoCredito[];

  cartoesSelecionados: CartaoCredito[];

  ngOnInit(): void {

    this.cartoesSelecionados = [];

    const cartaoCredito = new CartaoCredito();
    const pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    cartaoCredito.pessoa = pessoa;

    this.getCartoes(cartaoCredito);
  }

  getCartoes(cartaoCredito: CartaoCredito) {
    this.service.get(cartaoCredito, 'cartaocredito').subscribe( resultado => {
      this.cartoes = resultado?.entidades;
    });
  }

  selecionaCartao2(event, cartaoSelecionado: CartaoCredito) {
    if (event.target.checked) {
      if (this.cartoesSelecionados.length < 2) {
        this.cartoesSelecionados.push(cartaoSelecionado);
      } else {
        alert('Não é possivel selecionar mais que dois cartões.');
      }
    } else {
      const cartoesSemCartaoSelecionado: Array<CartaoCredito> = new Array<CartaoCredito>();
      this.cartoesSelecionados.forEach( cartao => {
        if (cartao.id !== cartaoSelecionado.id) {
          cartoesSemCartaoSelecionado.push(cartao);
        }
      });
      this.cartoesSelecionados = cartoesSemCartaoSelecionado;
    }
  }

  // selecionaCartao(cartaoSelecionado: CartaoCredito) {
  //
  //   if (this.cartoesSelecionados.length < 2) {
  //     if (this.cartoesSelecionados.includes(cartaoSelecionado)) {
  //       const cartoesSemCartaoSelecionado: Array<CartaoCredito> = new Array<CartaoCredito>();
  //       this.cartoesSelecionados.forEach( cartao => {
  //         if (cartao !== cartaoSelecionado) {
  //           cartoesSemCartaoSelecionado.push(this.cartoesSelecionados.pop());
  //         }
  //       });
  //       this.cartoesSelecionados = cartoesSemCartaoSelecionado;
  //     } else {
  //       this.cartoesSelecionados.push(cartaoSelecionado);
  //       sessionStorage.setItem('cartoesSelecionados', JSON.stringify(this.cartoesSelecionados));
  //     }
  //   } else { alert('Não é possivel selecionar mais que dois cartões.'); }
  // }

  continua() {
    if (this.cartoesSelecionados.length > 0) {
      sessionStorage.setItem('cartoesSelecionados', JSON.stringify(this.cartoesSelecionados));
      this.router.navigate(['/app-logado/order-resume']);
    } else {
      alert('É necessário escolher pelo menos um cartão.');
    }
  }

}
