import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { CartaoCredito } from 'src/model/domain/cartao-credito.model';
import { Pessoa } from 'src/model/domain/person.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-creditcard-list',
  templateUrl: './creditcard-list.component.html',
  styleUrls: ['./creditcard-list.component.css']
})
export class CreditcardListComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router) { }

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

  deletarCartao(cartaoSelecionado: CartaoCredito) {
    sessionStorage.setItem('cartaoSelecionado', JSON.stringify(cartaoSelecionado));
    this.router.navigate(['/app-logado/creditcard-delete']);
  }


}
