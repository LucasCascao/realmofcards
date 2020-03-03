import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-request-trade',
  templateUrl: './request-trade.component.html',
  styleUrls: ['./request-trade.component.css']
})
export class RequestTradeComponent implements OnInit {

  clients = new MockClient().pessoas;

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
    this.carta.value = 52.45;
  }

  filtrar(cartas: any) {

  }

}
