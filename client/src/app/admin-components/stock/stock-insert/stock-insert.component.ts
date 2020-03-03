import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-stock-insert',
  templateUrl: './stock-insert.component.html',
  styleUrls: ['./stock-insert.component.css']
})
export class StockInsertComponent implements OnInit {

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }

}
