import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../../mock/mock-card.model";

@Component({
  selector: 'app-devolution-stock',
  templateUrl: './devolution-stock.component.html',
  styleUrls: ['./devolution-stock.component.css']
})
export class DevolutionStockComponent implements OnInit {

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
  }

}
