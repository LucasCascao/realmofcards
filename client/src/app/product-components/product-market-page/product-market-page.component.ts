import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../mock/mock-card.model";

@Component({
  selector: 'app-product-market-page',
  templateUrl: './product-market-page.component.html',
  styleUrls: ['./product-market-page.component.css']
})
export class ProductMarketPageComponent implements OnInit {

  constructor() { }

  cartas = new MockCards().cards;

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }
}
