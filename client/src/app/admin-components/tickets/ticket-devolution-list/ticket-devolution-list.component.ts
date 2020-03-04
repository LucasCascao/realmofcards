import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../../mock/mock-card.model";
import {MockClient} from "../../../../mock/mock-cliente.model";

@Component({
  selector: 'app-ticket-devolution-list',
  templateUrl: './ticket-devolution-list.component.html',
  styleUrls: ['./ticket-devolution-list.component.css']
})
export class TicketDevolutionListComponent implements OnInit {

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
