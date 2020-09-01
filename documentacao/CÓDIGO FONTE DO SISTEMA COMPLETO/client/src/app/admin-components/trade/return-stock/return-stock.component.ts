import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-return-stock',
  templateUrl: './return-stock.component.html',
  styleUrls: ['./return-stock.component.css']
})
export class ReturnStockComponent implements OnInit {

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
  }

}
