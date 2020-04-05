import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../mock/mock-card.model';
import { UtilService } from 'src/services/util.service';
import { Carrinho } from 'src/model/domain/carrinho.model';
import { Person } from 'src/model/domain/person.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  carta = new MockCards().cards[0];

  carrinho: Carrinho;

  pessoa: Person;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.carrinho = new Carrinho();
    this.carrinho.pessoa = JSON.parse(sessionStorage?.getItem('pessoaLogada'));
    this.getCarrinho();
  }

  getCarrinho() {
    this.service.get(this.carrinho, 'carrinhos').subscribe(resultado => {
      this.carrinho = resultado?.entidades[resultado?.entidades.length - 1];
    });
  }

}
