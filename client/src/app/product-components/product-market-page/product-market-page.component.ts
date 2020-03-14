import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../mock/mock-card.model";
import {Person} from "../../../model/person.model";

@Component({
  selector: 'app-product-market-page',
  templateUrl: './product-market-page.component.html',
  styleUrls: ['./product-market-page.component.css']
})
export class ProductMarketPageComponent implements OnInit {

  constructor() { }

  client: Person;

  cartas = new MockCards().cards;

  ngOnInit(): void {
    this.client = JSON.parse(localStorage.getItem('userAutenticado'));
    console.log(this.client);
  }

  filtrar(cartas: any) {
  }
}
