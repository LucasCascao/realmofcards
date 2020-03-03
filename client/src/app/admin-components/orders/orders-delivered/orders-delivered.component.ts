import { Component, OnInit } from '@angular/core';
import {MockClient} from "../../../../mock/mock-cliente.model";
import {MockCards} from "../../../../mock/mock-card.model";

@Component({
  selector: 'app-orders-delivered',
  templateUrl: './orders-delivered.component.html',
  styleUrls: ['./orders-delivered.component.css']
})
export class OrdersDeliveredComponent implements OnInit {

  clients = new MockClient().pessoas;

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
    this.carta.value = 52.45;
  }

  filtrar(cartas: any) {

  }

}
