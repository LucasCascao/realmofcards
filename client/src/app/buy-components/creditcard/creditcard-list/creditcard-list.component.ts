import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { CartaoCredito } from 'src/model/domain/cartao-credito.model';
import { Person } from 'src/model/domain/person.model';

@Component({
  selector: 'app-creditcard-list',
  templateUrl: './creditcard-list.component.html',
  styleUrls: ['./creditcard-list.component.css']
})
export class CreditcardListComponent implements OnInit {

  constructor(private service: UtilService) { }

  cartoes: CartaoCredito[];

  idCartaoSelecionado: number;

  ngOnInit(): void {

    // tslint:disable-next-line:radix max-line-length
    this.idCartaoSelecionado = sessionStorage.getItem('cartaoSelecionado') != null ? Number.parseInt(sessionStorage.getItem('idCartaoSelecionado')) : null;

    const cartaoCredito = new CartaoCredito();
    const pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'))
    cartaoCredito.pessoa = pessoa;
    this.getCartoes(cartaoCredito);
  }

  getCartoes(cartaoCredito: CartaoCredito){
    this.service.get(cartaoCredito, 'cartaocredito').subscribe( resultado => {
      this.cartoes = resultado?.entidades;
    });
  }

  selecionaCartao(cartaoSelecionado: CartaoCredito) {

    let cartaoParaTroca: CartaoCredito;

    this.cartoes.forEach( cartao => {
      if (cartao.id === this.idCartaoSelecionado) {
        cartaoParaTroca = cartao;
      }
    });

    cartaoParaTroca.preferido = true;
  }

  alterarPreferido(cartaoCredito: CartaoCredito) {
    this.service.update(cartaoCredito, 'cartaoCredito').subscribe();
  }


}
