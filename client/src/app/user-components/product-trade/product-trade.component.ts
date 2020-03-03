import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../mock/mock-card.model';
import {MockClient} from '../../../mock/mock-cliente.model';

@Component({
  selector: 'app-product-trade',
  templateUrl: './product-trade.component.html'
})
export class ProductTradeComponent implements OnInit {

  carta = new MockCards().cards[0];

  cliente = new MockClient().pessoas[0];

  constructor() { }

  ngOnInit(): void {
  }

}
