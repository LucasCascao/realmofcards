import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';
import {MockCategory} from '../../../../mock/mock-categories.model';

@Component({
  selector: 'app-pending-orders',
  templateUrl: './pending-orders.component.html',
  styleUrls: ['./pending-orders.component.css']
})
export class PendingOrdersComponent implements OnInit {

  clients = new MockClient().pessoas;

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
    this.carta.value = 52.45;
  }

  filtrar(cartas: any) {

  }

}
