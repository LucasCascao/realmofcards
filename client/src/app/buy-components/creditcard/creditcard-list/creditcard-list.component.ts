import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { CartaoCredito } from 'src/model/domain/cartao-credito.model';
import { Person } from 'src/model/domain/person.model';
import { GLOBAL } from 'src/app/shared/global.util';

@Component({
  selector: 'app-creditcard-list',
  templateUrl: './creditcard-list.component.html',
  styleUrls: ['./creditcard-list.component.css']
})
export class CreditcardListComponent implements OnInit {

  constructor(private service: UtilService) { }

  cartoes: CartaoCredito[];

  pessoa: Person;

  cartaoCredito: CartaoCredito;

  ngOnInit(): void {
    
    this.pessoa = new Person();
    this.cartaoCredito = new CartaoCredito();

    this.pessoa.id = GLOBAL.pessoa.id;
    this.cartaoCredito.pessoa = this.pessoa;

    this.getCartoes();
  }

  async getCartoes(){
    await this.service.get(this.cartaoCredito, 'cartaocredito').subscribe( resultado => {
      this.cartoes = resultado?.entidades;
    });
  }
}
