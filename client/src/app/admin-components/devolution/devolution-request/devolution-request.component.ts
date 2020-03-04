import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../../mock/mock-card.model";
import {MockClient} from "../../../../mock/mock-cliente.model";

@Component({
  selector: 'app-devolution-request',
  templateUrl: './devolution-request.component.html',
  styleUrls: ['./devolution-request.component.css']
})
export class DevolutionRequestComponent implements OnInit {

  carta = new MockCards().cards[0];

  clients = new MockClient().pessoas;

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }

}
