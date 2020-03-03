import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../mock/mock-card.model';

@Component({
  selector: 'app-product-generate-trade-code',
  templateUrl: './product-generate-trade-code.component.html'
})
export class ProductGenerateTradeCodeComponent implements OnInit {

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
  }

}
