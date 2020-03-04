import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../../mock/mock-card.model";
import {MockClient} from "../../../../mock/mock-cliente.model";

@Component({
  selector: 'app-ticket-trade-list',
  templateUrl: './ticket-trade-list.component.html',
  styleUrls: ['./ticket-trade-list.component.css']
})
export class TicketTradeListComponent implements OnInit {

  tickets = [
    '999999999',
    '999999999',
    '999999999',
    '999999999',
    '999999999',
    '999999999',
    '999999999',
    '999999999',
    ]

  carta = new MockCards().cards[0];

  client = new MockClient().pessoas[0];

  filtrar(ticket: any){

  }

  constructor() { }

  ngOnInit(): void {
  }

}
