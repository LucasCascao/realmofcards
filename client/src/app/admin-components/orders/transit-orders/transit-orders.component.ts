import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-transit-orders',
  templateUrl: './transit-orders.component.html',
  styleUrls: ['./transit-orders.component.css']
})
export class TransitOrdersComponent implements OnInit {

  clients = new MockClient().pessoas;

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
    this.carta.value = 52.45;
  }

  filtrar(cartas: any) {

  }

}
