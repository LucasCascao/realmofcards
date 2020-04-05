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

  ngOnInit(): void {
    let cartaoCredito = new CartaoCredito();
    let pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'))
    cartaoCredito.pessoa = pessoa;
    this.getCartoes(cartaoCredito);
  }

  getCartoes(cartaoCredito: CartaoCredito){
    this.service.get(cartaoCredito, 'cartaocredito').subscribe( resultado => {
      this.cartoes = resultado?.entidades;
    });
  }
}
